package ttt.commandlineapp;

import org.junit.Test;
import ttt.commandlineapp.players.CommandLinePlayerFactory;
import ttt.commandlineapp.players.RandomPlayer;
import ttt.commandlineapp.prompt.PromptSpy;
import ttt.game.GameConfiguration;
import ttt.game.GameRules;
import ttt.game.board.Board;
import ttt.game.board.BoardFactoryStub;
import ttt.game.players.DelayedUnbeatablePlayer;
import ttt.game.players.UnbeatablePlayer;
import ttt.game.rules.TicTacToeGameConfiguration;
import ttt.game.rules.TicTacToeRules;

import java.io.StringReader;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.O;
import static ttt.game.PlayerSymbol.X;

public class UnbeatablePlayerIsUnbeatableTest {
    private static final int NUMBER_OF_GAMES = 100;
    private static final String PLAYER_CHOICE = "1\n";
    private static final String REPROMPT = "Y\n";
    private static final String DO_NOT_REPLAY = "N\n";

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyOpenTheGameIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new UnbeatablePlayer(X), new RandomPlayer(O, promptSpy));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGameIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new RandomPlayer(O, promptSpy), new UnbeatablePlayer(X));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatableVsUnbeatableHasNoGamesWonIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new UnbeatablePlayer(O), new UnbeatablePlayer(X));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), is(0));
    }

    @Test
    public void delayedUnbeatablePlayerNeverLoosesWhenTheyOpenTheGameIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)), new RandomPlayer(O, promptSpy));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void delayedUnbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGameIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new RandomPlayer(O, promptSpy), new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void delayedUnbeatableVsDelayedUnbeatableHasNoGamesWonIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        CommandLinePlayerFactory playerFactory = new CommandLinePlayerFactoryStub(new DelayedUnbeatablePlayer(O, new UnbeatablePlayer(O)), new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameConfiguration gameConfiguration = new TicTacToeGameConfiguration();
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameConfiguration, gameRules, promptSpy);

        gameWithManyRounds.startGame();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), is(0));
    }

    private String setupForGameWithBoardDimensionOf(int dimension) {
        String userInput = "";

        for (int gameNumber = 0; gameNumber < NUMBER_OF_GAMES - 1; gameNumber++) {
            userInput += dimension + "\n" + PLAYER_CHOICE + REPROMPT;
        }
        userInput += dimension + "\n" + PLAYER_CHOICE + DO_NOT_REPLAY;
        return userInput;
    }

    private Board[] emptyGridPerGameWithDimension(int dimension) {
        Board[] emptyGridPerGame = new Board[NUMBER_OF_GAMES];
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            emptyGridPerGame[i] = new Board(dimension);
        }
        return emptyGridPerGame;
    }
}
