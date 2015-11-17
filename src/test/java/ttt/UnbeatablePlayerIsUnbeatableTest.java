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

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyOpenTheGame() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactorySpy(new UnbeatablePlayer(X), new RandomPlayer(promptSpy, O));
        Game gameWithManyRounds = new Game(new Board(), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatablePlayerNeverLoosesWhenTheyDoNotOpenTheGame() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactorySpy(new RandomPlayer(promptSpy, O), new UnbeatablePlayer(X));
        Game gameWithManyRounds = new Game(new Board(), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), greaterThan(1));
    }

    @Test
    public void unbeatableVsUnbeatableHasNoGamesWon() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactorySpy(new UnbeatablePlayer(O), new UnbeatablePlayer(X));
        Game gameWithManyRounds = new Game(new Board(), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
        assertThat(promptSpy.getNumberOfTimesXHasWon(), is(0));
    }

    private String replayAndGameTypeInputForGame() {
        String userInput = "";
        for (int gameNumber = 0; gameNumber < NUMBER_OF_GAMES - 1; gameNumber++) {
            userInput += "1\nY\n";
        }
        userInput += "1\nN\n";
        return userInput;
    }
}
