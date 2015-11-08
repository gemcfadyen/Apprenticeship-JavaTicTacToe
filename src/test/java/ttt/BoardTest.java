package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ttt.PlayerSymbol.*;

public class BoardTest {

    @Test
    public void getsSymbolFromSpecifiedPosition() {
        Board board = new Board(X, X, O, X, VACANT, O, O, X, X);
        assertThat(board.getSymbolAt(4), is(VACANT));
    }

    @Test
    public void identifiesThatThereIsAFreeSpaceOnTheBoard() {
        Board board = new Board();
        assertTrue(board.hasFreeSpace());
    }

    @Test
    public void identifiesThatGivenSlotOnBoardIsVacant() {
        Board board = new Board();
        assertTrue(board.isVacantAt(1));
    }

    @Test
    public void identifiesThatGivenSlotOnBoardIsOccupied() {
        Board board = new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertFalse(board.isVacantAt(0));
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
    public void winningRowOfOIdentifiedInTopRow() {
        Board board = new Board(O, O, O, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowOfXIdentifiedInTopRow() {
        Board board = new Board(X, X, X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowOfXIdentifiedInMiddleRow() {
        Board board = new Board(VACANT, VACANT, VACANT, X, X, X, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowOfOIdentifiedInMiddleRow() {
        Board board = new Board(VACANT, VACANT, VACANT, O, O, O, VACANT, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowOfXIdentifiedInBottomRow() {
        Board board = new Board(VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, X, X, X);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRowOfOIdentifiedInBottomRow() {
        Board board = new Board(VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, O, O, O);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningLeftColumnOfXIdentified() {
        Board board = new Board(X, VACANT, VACANT, X, VACANT, VACANT, X, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningLeftColumnOfOIdentified() {
        Board board = new Board(O, VACANT, VACANT, O, VACANT, VACANT, O, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningMiddleColumnOfXIdentified() {
        Board board = new Board(VACANT, X, VACANT, VACANT, X, VACANT, VACANT, X, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningMiddleColumnOfOIdentified() {
        Board board = new Board(VACANT, O, VACANT, VACANT, O, VACANT, VACANT, O, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRightColumnOfOIdentified() {
        Board board = new Board(VACANT, VACANT, O, VACANT, VACANT, O, VACANT, VACANT, O);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningRightColumnOfXIdentified() {
        Board board = new Board(VACANT, VACANT, X, VACANT, VACANT, X, VACANT, VACANT, X);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningBackslashDiagonalOfXIdentified() {
        Board board = new Board(X, VACANT, VACANT, VACANT, X, VACANT, VACANT, VACANT, X);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningBackslashDiagonalOfOIdentified() {
        Board board = new Board(O, VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, O);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningForwardslashDiagonalOfOIdentified() {
        Board board = new Board(VACANT, VACANT, O, VACANT, O, VACANT, O, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningForwardslashDiagonalOfXIdentified() {
        Board board = new Board(VACANT, VACANT, X, VACANT, X, VACANT, X, VACANT, VACANT);
        assertThat(board.hasWinningCombination(), is(true));
    }

    @Test
    public void winningSymbolIdentifiedAsX() {
        Board board = new Board(VACANT, VACANT, X, VACANT, X, VACANT, X, VACANT, VACANT);
        assertThat(board.getWinningSymbol(), is(X));
    }

    @Test
    public void winningSymbolIdentifiedAsO() {
        Board board = new Board(VACANT, O, VACANT, VACANT, O, VACANT, VACANT, O, VACANT);
        assertThat(board.getWinningSymbol(), is(O));
    }

    @Test
    public void winningSymbolIdentifiedAsVacantIfNoWinsOnBoard() {
        Board board = new Board();
        assertThat(board.getWinningSymbol(), is(VACANT));
    }

    @Test
    public void updateBoardWithSpecificSymbolAtGivenPosition() {
        Board board = new Board();
        board.updateAt(2, X);

        assertThat(board.getSymbolAt(2), is(X));
    }

    @Test
    public void indicatesPositionLargerThanGridIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isWithinGridBoundary(9), is(false));
    }

    @Test
    public void indicatesPositionAtZeroIsInsideOfGrid() {
        Board board = new Board();
        assertThat(board.isWithinGridBoundary(0), is(true));
    }

    @Test
    public void indicatesPositionEightIsValid() {
        Board board = new Board();
        assertThat(board.isWithinGridBoundary(8), is(true));
    }

    @Test
    public void indicatesPositionLessThanZeroIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isWithinGridBoundary(-3), is(false));
    }

    @Test
    public void indicatesPositionIsWithinGridBoundary() {
        Board board = new Board();
        assertThat(board.isWithinGridBoundary(2), is(true));
    }

    @Test
    public void returnsHorizontalRows() {
        Board board = new Board(X, O, X, O, O, X, VACANT, VACANT, O);

        Line[] rows = board.getRows();

        PlayerSymbol[] expectedTopRow = new PlayerSymbol[]{ X, O, X};
        PlayerSymbol[] expectedMiddleRow = new PlayerSymbol[]{ O, O, X};
        PlayerSymbol[] expectedBottomRow = new PlayerSymbol[]{VACANT, VACANT, O};

        assertThat(rows.length, is(3));
        assertThat(rows[0].getSymbols(), is(expectedTopRow));
        assertThat(rows[1].getSymbols(), is(expectedMiddleRow));
        assertThat(rows[2].getSymbols(), is(expectedBottomRow));
    }
}