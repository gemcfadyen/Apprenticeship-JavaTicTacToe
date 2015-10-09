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

    @Test
    public void returnsTrueWhenBoardHasThreeMatchingSymbolsInTopRow() {
        Board board = new Board("X", "X", "X", "-", "-", "-", "-", "-", "-");
        assertThat(board.containsWinningRow(), is(true));
    }

    @Test
    public void returnsTrueWhenBoardHasThreeMatchingSymbolsInMiddleRow() {
        Board board = new Board("-", "-", "-", "-", "-", "-", "X", "X", "X");
        assertThat(board.containsWinningRow(), is(true));
    }

    @Test
    public void returnsTrueWhenBoardHasThreeMatchingSymbolsInBottomRow() {
        Board board = new Board("-", "-", "-", "X", "X", "X", "-", "-", "-");
        assertThat(board.containsWinningRow(), is(true));
    }
}
