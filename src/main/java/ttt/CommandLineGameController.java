package ttt;

import ttt.board.BoardFactory;
import ttt.gui.GameConfiguration;
import ttt.gui.GameRules;
import ttt.gui.TicTacToeGameConfiguration;
import ttt.player.PlayerFactory;
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


    public CommandLineGameController(GameConfiguration gameConfiguration, GameRules gameRules, Prompt commandLinePrompt) {
        this(gameConfiguration, commandLinePrompt);
        this.gameRules = gameRules;
    }

    public CommandLineGameController(GameConfiguration gameConfiguration, Prompt commandLinePrompt) {
        this.gameConfiguration = gameConfiguration;
        this.gamePrompt = commandLinePrompt;
    }

    public static void main(String... args) {
        CommandPrompt gamePrompt = buildPrompt();
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                new TicTacToeGameConfiguration(new BoardFactory(), new PlayerFactory(gamePrompt)),
                gamePrompt
        );
        commandLineGameController.startGame();
    }

    public void startGame() {
        ReplayOption replayOption = Y;

        while (replayOption.equals(Y)) {
            GameType gameType = getGameTypeFromPlayer();
            int dimension = getDimensionChoiceFromPlayer(gameType);
            gameRules = gameConfiguration.initialiseGame(gameType, dimension);

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
        setGameType(gameType);
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

    void playMatch() {
        while (gameInProgress()) {
            updateBoardWithPlayersMove();
            gameRules.togglePlayer();
        }
        displayResultsOfGame();
    }

    private ReplayOption getReplayOptionFromPlayer() {
        gamePrompt.presentReplayOption();
        return gamePrompt.readReplayOption();
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

    private void setGameType(GameType gameType) {
        this.gameType = gameType;
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
}