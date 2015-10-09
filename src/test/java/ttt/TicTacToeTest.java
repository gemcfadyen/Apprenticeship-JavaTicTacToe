package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class TicTacToeTest {

    @Test
    public void theGameIsDrawnWhenThereIsNoWinner() {
        Game game = new Game();

        String status = game.play();

        assertThat(status, is("Draw"));
    }
}