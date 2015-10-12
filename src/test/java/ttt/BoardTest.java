package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Georgina on 09/10/15.
 */
public class BoardTest {

    @Test
    public void getsSymbolFromSpecifiedPosition() {
        Board board = new Board();
        assertThat(board.getSymbolAt(5), is("-"));
    }

    @Test
    public void identifiesThatThereIsAFreeSpaceOnTheBoard() {
        Board board = new Board();
        assertTrue(board.hasFreeSpace());
    }

    @Test
    public void identifiesThatThereIsNoFreeSpaceOnTheBoard() {
        Board board = new Board("O", "O", "O", "X", "X", "O", "O", "X", "O");
        assertThat(board.hasFreeSpace(), is(false));
    }

    @Test
    public void noWinningRowWhenBoardIsAllVacant() {
        Board board = new Board();
        assertThat(board.hasWinningCombination(), is(false));
    }

    @Test
    public void winningRowIdentifiedInTopRow() {
        Board board = new Board("O", "O", "O", "-", "-", "-", "-", "-", "-");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowIdentifiedInMiddleRow() {
        Board board = new Board("-", "-", "-", "X", "X", "X", "-", "-", "-");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowIdentifiedInBottomRow() {
        Board board = new Board("-", "-", "-", "-", "-", "-", "X", "X", "X");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnIdentified() {
        Board board = new Board("X", "-", "-", "X", "-", "-", "X", "-", "-");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnInMiddleColumnIdentified() {
        Board board = new Board("-", "X", "-", "-", "X", "-", "-", "X", "-");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnInRightColumn() {
        Board board = new Board("-", "-", "O", "-", "-", "O", "-", "-", "O");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningBackSlashDiagonal() {
        Board board = new Board("X", "-", "-", "-", "X", "-", "-", "-", "X");
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningForwardSlashDiagonal() {
        Board board = new Board("-", "-", "O", "-", "O", "-", "O", "-", "-");
        assertThat(board.hasWinningCombination(), is(true));
    }
}
