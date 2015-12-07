package ttt;

import ttt.board.BoardFactory;
import ttt.gui.GameRules;
import ttt.gui.TicTacToeRules;
import ttt.player.PlayerFactory;
import ttt.ui.CommandPrompt;
import ttt.ui.PrettyFormatter;
import ttt.ui.Prompt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import static ttt.ReplayOption.Y;

public class CommandLineGameController {
    private GameRules gameRules;
    private Prompt gamePrompt;

    public CommandLineGameController(GameRules gameRules, Prompt commandLinePrompt) {
        this.gameRules = gameRules;
        this.gamePrompt = commandLinePrompt;
    }

    public static void main(String... args) {
        CommandPrompt gamePrompt = buildPrompt();
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                buildGameRules(gamePrompt),
                gamePrompt
        );
        commandLineGameController.startGame();
    }

    public void startGame() {
        ReplayOption replayOption = Y;

        while (replayOption.equals(Y)) {
            GameType gameType = getGameTypeFromPlayer();
            int dimension = getDimensionChoiceFromPlayer(gameType);
            initialiseGame(dimension);

            playMatch();

            replayOption = gamePrompt.getReplayOption();
        }
    }

    GameType getGameTypeFromPlayer() {
        List<GameType> allGameTypes = gameRules.getGameTypes();
        presentGameTypes(allGameTypes);
        return readGameType(allGameTypes);
    }

    int getDimensionChoiceFromPlayer(GameType gameType) {
        presentBoardDimensionsFor(gameType);
        return readDimension(gameType.dimensionUpperBoundary());
    }

    void playMatch() {
        while (gameInProgress()) {
            updateBoardWithPlayersMove();
            gameRules.togglePlayer();
        }
        displayResultsOfGame();
    }

    void updateBoardWithPlayersMove() {
        String nextMove = gameRules.getCurrentPlayersNextMove();
        playMove(nextMove);
    }

    void playMove(String nextMove) {
        gameRules.playMoveAt(nextMove);
    }

    boolean gameInProgress() {
        return gameRules.boardHasFreeSpace() && !gameRules.hasWinner();
    }

    void displayResultsOfGame() {
        printExitMessage();
    }

    private void presentGameTypes(List<GameType> allGameTypes) {
        gamePrompt.presentGameTypes(allGameTypes);
    }

    private GameType readGameType(List<GameType> gameTypes) {
        GameType gameType = gamePrompt.readGameType(gameTypes);
        gameRules.storeGameType(gameType);
        return gameType;
    }

    private void presentBoardDimensionsFor(GameType gameType) {
        String largestDimension = gameRules.getDimension(gameType);
        gamePrompt.presentGridDimensionsUpTo(largestDimension);
    }

    private int readDimension(int largestDimension) {
        return gamePrompt.readBoardDimension(largestDimension);
    }

    private void initialiseGame(int dimension) {
        gameRules.initialiseGame(String.valueOf(dimension));
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
                new OutputStreamWriter(System.out),
                new PrettyFormatter()
        );
    }

    private static TicTacToeRules buildGameRules(CommandPrompt gamePrompt) {
        return new TicTacToeRules(new BoardFactory(), new PlayerFactory(gamePrompt));
    }
}