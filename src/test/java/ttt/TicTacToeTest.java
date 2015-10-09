package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class TicTacToeTest {

    @Test
    public void reportsDrawWhenBoardIsFull() {
        Board board = new Board("X", "X", "O", "O", "X", "X", "O", "O", "O");
        Game game = new Game(board);

        String status = game.play();

        assertThat(status, is("Draw"));
    }
}