package ttt.game.board;

import org.junit.Test;
import ttt.game.PlayerSymbol;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ttt.game.PlayerSymbol.*;

public class BoardTest {

    @Test
    public void creates3x3Board() {
        Board board = new Board(3);
        assertThat(board.getVacantPositions(), is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
    }

    @Test
    public void creates4x4Board() {
        Board board = new Board(4);
        assertThat(board.getVacantPositions(), is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));
    }

    @Test
    public void getsSymbolFromSpecifiedPosition() {
        Board board = new Board(X, X, O, X, VACANT, O, O, X, X);
        assertThat(board.getSymbolAt(4), is(VACANT));
    }

    @Test
    public void identifiesThatThereIsAFreeSpaceOnTheBoard() {
        Board board = new Board(3);
        assertTrue(board.hasFreeSpace());
    }

    @Test
    public void identifiesThatGivenSlotOnBoardIsVacant() {
        Board board = new Board(3);
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
        Board board = new Board(3);
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
        Board board = new Board(3);
        assertThat(board.getWinningSymbol(), is(VACANT));
    }

    @Test
    public void updateBoardWithSpecificSymbolAtGivenPosition() {
        Board board = new Board(3);
        board.updateAt(2, X);

        assertThat(board.getSymbolAt(2), is(X));
    }

    @Test
    public void indicatesPositionLargerThanGridIsOutsideOfGrid() {
        Board board = new Board(3);
        assertThat(board.isWithinGridBoundary(9), is(false));
    }

    @Test
    public void indicatesPositionAtZeroIsInsideOfGrid() {
        Board board = new Board(3);
        assertThat(board.isWithinGridBoundary(0), is(true));
    }

    @Test
    public void indicatesPositionEightIsValid() {
        Board board = new Board(3);
        assertThat(board.isWithinGridBoundary(8), is(true));
    }

    @Test
    public void indicatesPositionLessThanZeroIsOutsideOfGrid() {
        Board board = new Board(3);
        assertThat(board.isWithinGridBoundary(-3), is(false));
    }

    @Test
    public void indicatesPositionIsWithinGridBoundary() {
        Board board = new Board(3);
        assertThat(board.isWithinGridBoundary(2), is(true));
    }

    @Test
    public void allPositionsReturnedForEmptyBoard() {
        Board board = new Board(3);

        List<Integer> freePositions = board.getVacantPositions();

        assertThat(freePositions.size(), is(9));
        assertThat(freePositions.containsAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)), is(true));
    }

    @Test
    public void noFreePositionsOnFullyOccupiedBoard() {
        Board board = new Board(X, O, X, O, X, O, X, O, X);

        List<Integer> freePositions = board.getVacantPositions();

        assertThat(freePositions.size(), is(0));
    }

    @Test
    public void getsFreePositions() {
        Board board = new Board(X, VACANT, VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT);

        List<Integer> freePositions = board.getVacantPositions();

        assertThat(freePositions.size(), is(7));
        assertThat(freePositions.containsAll(Arrays.asList(1, 2, 3, 4, 6, 7, 8)), is(true));
    }


    @Test
    public void returnsHorizontalRows() {
        Board board = new Board(X, O, X, O, O, X, VACANT, VACANT, O);

        List<Line> rows = board.getRows();

        PlayerSymbol[] expectedTopRow = new PlayerSymbol[]{X, O, X};
        PlayerSymbol[] expectedMiddleRow = new PlayerSymbol[]{O, O, X};
        PlayerSymbol[] expectedBottomRow = new PlayerSymbol[]{VACANT, VACANT, O};

        assertThat(rows.size(), is(3));
        assertThat(rows.get(0).getSymbols(), is(expectedTopRow));
        assertThat(rows.get(1).getSymbols(), is(expectedMiddleRow));
        assertThat(rows.get(2).getSymbols(), is(expectedBottomRow));
    }
}