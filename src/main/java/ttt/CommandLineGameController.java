package ttt;

import ttt.board.BoardFactory;
import ttt.gui.GameConfiguration;
import ttt.gui.GameRules;
import ttt.gui.TicTacToeGameConfiguration;
import ttt.gui.TicTacToeRules;
import ttt.player.CommandLinePlayerFactory;
import ttt.ui.CommandPrompt;
import ttt.ui.PrettyFormatter;
import ttt.ui.Prompt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import static ttt.ReplayOption.Y;

public class CommandLineGameController {
    private final GameConfiguration gameConfiguration;
    private GameRules gameRules;
    private Prompt gamePrompt;
    private GameType gameType;

//    public CommandLineGameController(GameConfiguration gameConfiguration{
    public CommandLineGameController(GameConfiguration gameConfiguration, GameRules gameRules, Prompt commandLinePrompt) {
        this.gameConfiguration = gameConfiguration;
        this.gameRules = gameRules;
        this.gamePrompt = commandLinePrompt;
    }

    public static void main(String... args) {
        CommandPrompt gamePrompt = buildPrompt();
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                new TicTacToeGameConfiguration(),
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

            replayOption = getReplayOptionFromPlayer();
        }
    }

    GameType getGameTypeFromPlayer() {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        presentGameTypes(allGameTypes);
        return readGameType(allGameTypes);
    }

    private void presentGameTypes(List<GameType> allGameTypes) {
        gamePrompt.presentGameTypes(allGameTypes);
    }

    private GameType readGameType(List<GameType> gameTypes) {
        GameType gameType = gamePrompt.readGameType(gameTypes);
        this.gameType = gameType;
        return gameType;
    }

    int getDimensionChoiceFromPlayer(GameType gameType) {
        presentBoardDimensionsFor(gameType);
        return readDimension(gameType.dimensionUpperBoundary());
    }

    private void presentBoardDimensionsFor(GameType gameType) {
        String largestDimension = gameConfiguration.getDimension(gameType);
        gamePrompt.presentGridDimensionsUpTo(largestDimension);
    }

    private int readDimension(int largestDimension) {
        return gamePrompt.readBoardDimension(largestDimension);
    }

    private void initialiseGame(int dimension) {
        gameRules.initialiseGame(gameType, String.valueOf(dimension));
    }

    void playMatch() {
        while (gameInProgress()) {
            updateBoardWithPlayersMove();
        }
        displayResultsOfGame();
    }

    private ReplayOption getReplayOptionFromPlayer() {
        ReplayOption replayOption;
        gamePrompt.presentReplayOption();
        replayOption = gamePrompt.readReplayOption();
        return replayOption;
    }

    void updateBoardWithPlayersMove() {
        int nextMove = gameRules.getCurrentPlayersNextMove();
        playMove(nextMove);
    }

    void playMove(int nextMove) {
        gameRules.takeTurn(nextMove);
    }

    boolean gameInProgress() {
        return gameRules.gameInProgress();
    }

    void displayResultsOfGame() {
        printExitMessage();
    }

    GameType getGameType() {
        return gameType;
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

    private static GameRules buildGameRules(CommandPrompt gamePrompt) {
        return new TicTacToeRules(new BoardFactory(), new CommandLinePlayerFactory(gamePrompt));
    }
}