package ttt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ttt.PlayerSymbol.*;

public class BoardTest {

    @Test
    public void getsSymbolFromSpecifiedPosition() {
        Board board = new Board(X, X, O, X, VACANT, O, O, X, X);
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
        assertThat(board.isValidPositionAt(1), is(false));
    }

    @Test
    public void indicatesPositionLargerThanGridIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(10), is(false));
    }

    @Test
    public void indicatesPositionAtZeroIsOutsideOfGrid() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(0), is(false));
    }

    @Test
    public void indicatesPositionNineIsValid() {
        Board board = new Board();
        assertThat(board.isValidPositionAt(9), is(true));
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

    @Test
    public void returnsHorizontalRows() {
        Board board = new Board(X, O, X, O, O, X, VACANT, VACANT, O);

        Cell[][] rows = board.getRows();

        Cell[] expectedTopRow = new Cell[]{new Cell(1, X), new Cell(2, O), new Cell(3, X)};
        Cell[] expectedMiddleRow = new Cell[]{new Cell(4, O), new Cell(5, O), new Cell(6, X)};
        Cell[] expectedBottomRow = new Cell[]{new Cell(7, VACANT), new Cell(8, VACANT), new Cell(9, O)};
        assertThat(rows.length, is(3));
        assertThat(rows[0], is(expectedTopRow));
        assertThat(rows[1], is(expectedMiddleRow));
        assertThat(rows[2], is(expectedBottomRow));
    }
}