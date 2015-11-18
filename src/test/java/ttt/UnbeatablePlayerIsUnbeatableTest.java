package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.player.PlayerFactory;
import ttt.player.UnbeatablePlayer;

import java.io.StringReader;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class UnbeatablePlayerIsUnbeatableTest {
    private static final int NUMBER_OF_GAMES = 100;
    private static final String DIMENSION = "3\n";
    private static final String PLAYER_CHOICE = "1\n";
    private static final String REPROMPT = "Y\n";
    private static final String DO_NOT_REPLAY = "N\n";

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyOpenTheGame() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactoryStub(new UnbeatablePlayer(X), new RandomPlayer(O, promptSpy));
        Game gameWithManyRounds = new Game(new BoardFactoryStub(emptyGridPerGame()), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGame() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactoryStub(new RandomPlayer(O, promptSpy), new UnbeatablePlayer(X));
        Game gameWithManyRounds = new Game(new BoardFactoryStub(emptyGridPerGame()), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatableVsUnbeatableHasNoGamesWon() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactoryStub(new UnbeatablePlayer(O), new UnbeatablePlayer(X));
        Game gameWithManyRounds = new Game(new BoardFactoryStub(emptyGridPerGame()), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), is(0));
    }

    private String replayAndGameTypeInputForGame() {
        String userInput = "";

        for (int gameNumber = 0; gameNumber < NUMBER_OF_GAMES - 1; gameNumber++) {
            userInput += DIMENSION + PLAYER_CHOICE + REPROMPT;
        }
        userInput += DIMENSION + PLAYER_CHOICE + DO_NOT_REPLAY;
        return userInput;
    }

    private Board[] emptyGridPerGame() {
        Board[] emptyGridPerGame = new Board[NUMBER_OF_GAMES];
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            emptyGridPerGame[i] = new Board(3);
        }
        return emptyGridPerGame;
    }
}
