package ttt.commandlineapp;

import ttt.commandlineapp.formatting.ColourText;
import ttt.commandlineapp.formatting.PrettyBoard;
import ttt.commandlineapp.formatting.StandardTextPresenter;
import ttt.commandlineapp.players.CommandLinePlayerFactory;
import ttt.commandlineapp.prompt.CommandPrompt;
import ttt.commandlineapp.prompt.Prompt;
import ttt.game.*;
import ttt.game.board.BoardFactory;
import ttt.game.rules.TicTacToeGameConfiguration;
import ttt.game.rules.TicTacToeRules;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import static ttt.game.ReplayOption.Y;

public class CommandLineGameController {
    private final GameConfiguration gameConfiguration;
    private GameRules gameRules;
    private Prompt gamePrompt;
    private GameType gameType;

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
        return readDimension(gameType.dimensionLowerBoundary(), gameType.dimensionUpperBoundary());
    }

    private void presentBoardDimensionsFor(GameType gameType) {
        gamePrompt.presentGridDimensionsBetween(gameType.dimensionLowerBoundary(), gameType.dimensionUpperBoundary());
    }

    private int readDimension(int lowerDimension, int largestDimension) {
        return gamePrompt.readBoardDimension(lowerDimension, largestDimension);
    }

    private void initialiseGame(int dimension) {
        gameRules.initialiseGame(gameType, dimension);
    }

    void playMatch() {
        gameRules.playGame();
        printExitMessage();
    }

    private ReplayOption getReplayOptionFromPlayer() {
        ReplayOption replayOption;
        gamePrompt.presentReplayOption();
        replayOption = gamePrompt.readReplayOption();
        return replayOption;
    }

    GameType getGameType() {
        return gameType;
    }

    void printExitMessage() {
        if (gameRules.noWinnerYet()) {
            gamePrompt.printsDrawMessage(gameRules.getBoard());
        } else {
            gamePrompt.printsWinningMessage(gameRules.getBoard(), gameRules.getWinningSymbol());
        }
    }

    private static CommandPrompt buildPrompt() {
        return new CommandPrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out),
                new PrettyBoard(),
                new ColourText(new StandardTextPresenter()));
    }

    private static GameRules buildGameRules(CommandPrompt gamePrompt) {
        return new TicTacToeRules(new BoardFactory(), new CommandLinePlayerFactory(gamePrompt));
    }
}