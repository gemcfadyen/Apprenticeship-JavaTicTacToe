package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ttt.PlayerSymbol.*;

public class BoardTest {

    @Test
    public void getsSymbolFromSpecifiedPosition() {
        Board board = new Board();
        assertThat(board.getSymbolAt(5), is(VACANT));
    }

    @Test
    public void identifiesThatThereIsAFreeSpaceOnTheBoard() {
        Board board = new Board();
        assertTrue(board.hasFreeSpace());
    }

    @Test
    public void identifiesThatThereIsNoFreeSpaceOnTheBoard() {
        Board board = new Board(O, O, O, X, X, O, O, X, O);
        assertThat(board.hasFreeSpace(), is(false));
    }

    @Test
    public void noWinningRowWhenBoardIsAllVacant() {
        Board board = new Board();
        assertThat(board.hasWinningCombination(), is(false));
    }

    @Test
    public void noWinnerIdentifiedForBoardWithoutThreeMatchingSymbolsInWinningFormation() {
        Board board = new Board(VACANT, VACANT, O, VACANT, O, VACANT, X, X, VACANT);
        assertThat(board.hasWinningCombination(), is(false));
    }

    @Test
    public void winningRowIdentifiedInTopRow() {
        Board board = new Board(O, O, O, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowIdentifiedInMiddleRow() {
        Board board = new Board(VACANT, VACANT, VACANT, X, X, X, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowIdentifiedInBottomRow() {
        Board board = new Board(VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, X, X, X);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnIdentified() {
        Board board = new Board(X, VACANT, VACANT, X, VACANT, VACANT, X, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnInMiddleColumnIdentified() {
        Board board = new Board(VACANT, X, VACANT, VACANT, X, VACANT, VACANT, X, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningColumnInRightColumn() {
        Board board = new Board(VACANT, VACANT, O, VACANT, VACANT, O, VACANT, VACANT, O);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningBackSlashDiagonal() {
        Board board = new Board(X, VACANT, VACANT, VACANT, X, VACANT, VACANT, VACANT, X);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningForwardSlashDiagonal() {
        Board board = new Board(VACANT, VACANT, O, VACANT, O, VACANT, O, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void updateBoardWithSpecificSymbolAtGivenPosition() {
        Board board = new Board();
        board.updateAt(2, X);

        assertThat(board.getSymbolAt(2), is(X));
    }

    @Test
    public void indicatesUnoccupiedPositionOnGridIsVacant() {
        Board board = new Board();

        assertThat(board.isValidPositionAt(3), is(true));
    }

    @Test
    public void indicatesOccupiedPositionIsNotVacant() {
        Board board = new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertThat(board.isValidPositionAt(0), is(false));
    }

    @Test
    public void indicatesPositionLargerThanGridIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(10), is(false));
    }

    @Test
    public void indicatesPositionLessThanZeroIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(-3), is(false));
    }

    @Test
    public void indicatesPositionIsWithinGridBoundary() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(2), is(true));
    }
}

