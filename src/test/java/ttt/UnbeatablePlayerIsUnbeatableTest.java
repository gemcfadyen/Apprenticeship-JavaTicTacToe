package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.gui.GameRules;
import ttt.gui.TicTacToeRules;
import ttt.player.DelayedUnbeatablePlayer;
import ttt.player.PlayerFactory;
import ttt.player.UnbeatablePlayer;

import java.io.StringReader;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class UnbeatablePlayerIsUnbeatableTest {
    private static final int NUMBER_OF_GAMES = 10;
    private static final String PLAYER_CHOICE = "1\n";
    private static final String REPROMPT = "Y\n";
    private static final String DO_NOT_REPLAY = "N\n";

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyOpenTheGameIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new UnbeatablePlayer(X), new RandomPlayer(O, promptSpy));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGameIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new RandomPlayer(O, promptSpy), new UnbeatablePlayer(X));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatableVsUnbeatableHasNoGamesWonIn3x3() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(3)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new UnbeatablePlayer(O), new UnbeatablePlayer(X));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(3));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), is(0));
    }

    @Test
    public void delayedUnbeatablePlayerNeverLoosesWhenTheyOpenTheGameIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)), new RandomPlayer(O, promptSpy));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void delayedUnbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGameIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new RandomPlayer(O, promptSpy), new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void delayedUnbeatableVsDelayedUnbeatableHasNoGamesWonIn4x4() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(setupForGameWithBoardDimensionOf(4)));
        PlayerFactory playerFactory = new PlayerFactoryStub(new DelayedUnbeatablePlayer(O, new UnbeatablePlayer(O)), new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X)));
        BoardFactoryStub boardFactory = new BoardFactoryStub(emptyGridPerGameWithDimension(4));
        GameRules gameRules = new TicTacToeRules(boardFactory, playerFactory);
        CommandLineGameController gameWithManyRounds = new CommandLineGameController(gameRules, promptSpy, playerFactory);

        gameWithManyRounds.play();

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
