package ttt;

import java.util.Arrays;
import java.util.function.Function;

import static ttt.PlayerSymbol.*;

public class Board {
    protected static final int BOARD_DIMENSION = 3;
    private static final int NUMBER_OF_SLOTS = BOARD_DIMENSION * BOARD_DIMENSION;
    private Cell[] grid = new Cell[BOARD_DIMENSION * BOARD_DIMENSION];

    public Board() {
        for (int cellIndex = 0; cellIndex < NUMBER_OF_SLOTS; cellIndex++) {
            grid[cellIndex] = new Cell(calculateOffsetFor(cellIndex), VACANT);
        }
    }

    public Board(PlayerSymbol... initialGridLayout) {
        for (int cellIndex = 0; cellIndex < NUMBER_OF_SLOTS; cellIndex++) {
            this.grid[cellIndex] = new Cell(calculateOffsetFor(cellIndex), initialGridLayout[cellIndex]);
        }
    }

    public Cell[][] getRows() {
        LineGenerator lines = new LineGenerator(grid);
        return new Cell[][]{lines.topRow(), lines.middleRow(), lines.bottomRow()};
    }

    public boolean hasWinningCombination() {
        return hasWinningRow() || hasWinningColumn() || hasWinningDiagonal();
    }

    public PlayerSymbol getSymbolAt(int offset) {
        return grid[calculateIndexFor(offset)].getSymbol();
    }

    public boolean hasFreeSpace() {
        for (int cellIndex = 0; cellIndex < NUMBER_OF_SLOTS; cellIndex++) {
            if (isVacantAt(cellIndex)) {
                return true;
            }
        }
        return false;
    }

    public void updateAt(int offset, PlayerSymbol symbol) {
        grid[calculateIndexFor(offset)].setSymbol(symbol);
    }

    private int calculateOffsetFor(int cellIndex) {
        return cellIndex + 1;
    }

    public boolean isValidPositionAt(int offset) {
        return isWithinGridBoundary(calculateIndexFor(offset)) && isVacantAt(calculateIndexFor(offset));
    }

    private int calculateIndexFor(int offset) {
        return offset - 1;
    }

    private boolean hasWinningRow() {
        return checkForWinIn(getRows());
    }

    private Function<Cell[], PlayerSymbol[]> getTheSymbolsFromCells() {
        return cells -> {
            PlayerSymbol[] symbols = new PlayerSymbol[cells.length];
            for (int cellIndex = 0; cellIndex < cells.length; cellIndex++) {
                symbols[cellIndex] = cells[cellIndex].getSymbol();
            }
            return symbols;
        };
    }

    private boolean checkForWinIn(Cell[][] rows) {
        for (Cell[] row : rows) {
            if (Arrays.equals(getTheSymbolsFromCells().apply(row), new PlayerSymbol[]{X, X, X})
                    || Arrays.equals(getTheSymbolsFromCells().apply(row), new PlayerSymbol[]{O, O, O})) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningColumn() {
        LineGenerator lines = new LineGenerator(grid);
        Cell[][] columns = {lines.leftColumn(), lines.middleColumn(), lines.rightColumn()};

        return checkForWinIn(columns);
    }

    private boolean hasWinningDiagonal() {
        LineGenerator lines = new LineGenerator(grid);
        Cell[][] diagonalRows = {lines.backslashDiagonal(), lines.forwardslashDiagonal()};

        return checkForWinIn(diagonalRows);
    }

    private boolean isVacantAt(int cellIndex) {
        return grid[cellIndex].getSymbol() == VACANT;
    }

    private boolean isWithinGridBoundary(int offset) {
        return offset >= 0 && offset < NUMBER_OF_SLOTS;
    }
}