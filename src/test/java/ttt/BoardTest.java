package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class BoardTest {

    @Test
    public void returnsFalseWhenBoardHasNoRowsOfSameSymbol() {
        Board board = new Board();
        assertThat(board.containsWinningRow(), is(false));
    }
}
