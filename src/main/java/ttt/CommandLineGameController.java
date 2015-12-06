package ttt;

import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.gui.GameRules;
import ttt.gui.TicTacToeRules;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.ui.CommandPrompt;
import ttt.ui.Prompt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import static ttt.ReplayOption.Y;

//todo implements gamecontroller
public class CommandLineGameController {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private PlayerFactory playerFactory;
    private GameRules gameRules;
    private Board board;
    private Prompt gamePrompt;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;

    CommandLineGameController(Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.board = board;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    //todo remove factories
    public CommandLineGameController(GameRules gameRules, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.gameRules = gameRules;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
        this.board = new Board(3);
    }

    //todo remove factories
    public CommandLineGameController(GameRules gameRules, Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
        this(board, gamePrompt, playerFactory);
        this.gameRules = gameRules;
    }

    public CommandLineGameController(GameRules gameRules, Prompt commandLinePrompt) {
        this.gameRules = gameRules;
        this.gamePrompt = commandLinePrompt;
    }

    public static void main(String... args) {
        TicTacToeRules gameRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory());
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                buildPrompt(),
                new PlayerFactory()
        );
        commandLineGameController.play();
    }

    public void play() {
        ReplayOption replayOption = Y;
        while (replayOption.equals(Y)) {
            presentGameTypes();
            GameType gameType = readGameType();

            presentBoardDimensionsFor(gameType);
            int dimension = readDimension(gameType.dimensionUpperBoundary());

            gameRules.initialiseGame(String.valueOf(dimension));

            Player[] players = setupPlayers(GameType.HUMAN_VS_HUMAN, dimension);
            playMatch(players);
            replayOption = gamePrompt.getReplayOption();
        }
    }

    void playMatch(Player[] players) {

        while (gameInProgress()) {
            updateBoardWithPlayersMove(players[currentPlayerIndex]);
            currentPlayerIndex = toggle(currentPlayerIndex);
        }
        displayResultsOfGame();
    }

    boolean gameInProgress() {
        return board.hasFreeSpace() && !board.hasWinningCombination();
    }

    void updateBoardWithPlayersMove(Player player) {
        int nextMove = player.chooseNextMoveFrom(board);
        board.updateAt(nextMove, player.getSymbol());
    }

    Player[] setupPlayers(GameType gameType, int dimension) {
        return createPlayersFor(gameType, dimension);
    }

    void presentGameTypes() {
        List<GameType> allGameTypes = gameRules.getGameTypes();
        gamePrompt.presentGameTypes(allGameTypes);
    }

    GameType readGameType() {
        GameType gameType = gamePrompt.readGameType();
        gameRules.storeGameType(gameType);
        return gameType;
    }

    void presentBoardDimensionsFor(GameType gameType) {
        String largestDimension = gameRules.getDimension(gameType);
        gamePrompt.presentGridDimensionsUpTo(largestDimension);
    }

    int readDimension(int largestDimension) {
        return gamePrompt.readBoardDimension(largestDimension);
    }

    void displayResultsOfGame() {
        gamePrompt.print(board);
        printExitMessage(board.hasWinningCombination());
    }

    private Player[] createPlayersFor(GameType gameType, int dimension) {
        return playerFactory.createPlayers(gameType, gamePrompt, dimension);
    }

    private int toggle(int currentPlayerIndex) {
        return currentPlayerIndex == PLAYER_ONE_INDEX ? PLAYER_TWO_INDEX : PLAYER_ONE_INDEX;
    }

    private void printExitMessage(boolean hasWinner) {
        if (!hasWinner) {
            gamePrompt.printDrawMessage();
        } else {
            gamePrompt.printWinningMessageFor(board.getWinningSymbol());
        }
    }

    private static CommandPrompt buildPrompt() {
        return new CommandPrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );
    }


}