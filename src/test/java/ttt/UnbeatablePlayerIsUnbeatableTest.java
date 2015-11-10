package ttt;

import org.junit.Ignore;
import org.junit.Test;
import ttt.board.Board;
import ttt.player.PlayerFactory;
import ttt.player.UnbeatablePlayer;

import java.io.StringReader;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

@Ignore
public class UnbeatablePlayerIsUnbeatableTest {
    private static final int NUMBER_OF_GAMES = 10;

    @Test
    public void unbeatablePlayerNeverLooses() {
        PromptSpy promptSpy = new PromptSpy(new StringReader(replayAndGameTypeInputForGame()));
        PlayerFactory playerFactory = new PlayerFactorySpy(new RandomPlayer(promptSpy, O), new UnbeatablePlayer(promptSpy, X));
        Game gameWithManyRounds = new Game(new Board(), promptSpy, playerFactory);

        gameWithManyRounds.play();

        assertThat(promptSpy.getNumberOfTimesOHasWon(), is(0));
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
