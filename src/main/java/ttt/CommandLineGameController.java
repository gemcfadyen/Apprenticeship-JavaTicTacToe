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
    private GameRules gameRules;


    private PlayerFactory playerFactory;
    private Prompt gamePrompt;

    CommandLineGameController(Prompt gamePrompt, PlayerFactory playerFactory) {
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    //todo remove factories
    public CommandLineGameController(GameRules gameRules, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.gameRules = gameRules;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    //todo remove factories
    public CommandLineGameController(GameRules gameRules, Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
        this(gamePrompt, playerFactory);
        this.gameRules = gameRules;
    }

    public CommandLineGameController(GameRules gameRules, Prompt commandLinePrompt) {
        this.gameRules = gameRules;
        this.gamePrompt = commandLinePrompt;
    }

    public static void main(String... args) {
        CommandPrompt gamePrompt = buildPrompt();
        TicTacToeRules gameRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(gamePrompt));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                gamePrompt
//                new PlayerFactory()
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

            playMatch();
            replayOption = gamePrompt.getReplayOption();
        }
    }

    void playMatch() {
        while (gameInProgress()) {
            updateBoardWithPlayersMove();
            gameRules.togglePlayer();
        }
        displayResultsOfGame();
    }

    boolean gameInProgress() {
        return gameRules.boardHasFreeSpace() && !gameRules.hasWinner();
    }

    void updateBoardWithPlayersMove() {
        String nextMove = gameRules.getCurrentPlayersNextMove();
        gameRules.playMoveAt(nextMove);
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
        printExitMessage();
    }

    private Player[] createPlayersFor(GameType gameType, int dimension) {
        return playerFactory.createPlayers(gameType, dimension);
    }

    private void printExitMessage() {
        if (!gameRules.hasWinner()) {
            gamePrompt.printsDrawMessage(gameRules.getBoard());
        } else {
            gamePrompt.printsWinningMessage(gameRules.getBoard(), gameRules.getWinningSymbol());
        }
    }

    private static CommandPrompt buildPrompt() {
        return new CommandPrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );
    }


}