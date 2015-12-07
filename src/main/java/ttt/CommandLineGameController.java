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
        TicTacToeRules gameRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(gamePrompt));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                gamePrompt
        );
        commandLineGameController.startGame();
    }

    public void startGame() {
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
        playMove(nextMove);
    }

    public void playMove(String nextMove) {
        gameRules.playMoveAt(nextMove);
    }

    public void presentGameTypes() {
        List<GameType> allGameTypes = gameRules.getGameTypes();
        gamePrompt.presentGameTypes(allGameTypes);
    }

    GameType readGameType() {
        GameType gameType = gamePrompt.readGameType();
        gameRules.storeGameType(gameType);
        return gameType;
    }

    public void presentBoardDimensionsFor(GameType gameType) {
        String largestDimension = gameRules.getDimension(gameType);
        gamePrompt.presentGridDimensionsUpTo(largestDimension);
    }

    int readDimension(int largestDimension) {
        return gamePrompt.readBoardDimension(largestDimension);
    }

    void displayResultsOfGame() {
        printExitMessage();
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