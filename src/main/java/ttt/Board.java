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
        LineGenerator lineGenerator = new LineGenerator(grid);
        return checkForWinIn(lineGenerator.linesForAllDirections());
    }

    public PlayerSymbol getWinningSymbol() {
        if (hasWinningCombination()) {

            for (Cell[] row : getLinesFromGrid()) {
                if (containsOnly(X, row)) {
                    return X;
                }
                if (containsOnly(O, row)) {
                    return O;
                }
            }
        }
        return VACANT;

    }

    public PlayerSymbol getSymbolAt(int offset) {
        return grid[calculateIndexFor(offset)].getSymbol();
    }

    public boolean isValidPositionAt(int offset) {
        return isWithinGridBoundary(calculateIndexFor(offset)) && isVacantAt(calculateIndexFor(offset));
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

    private int calculateIndexFor(int offset) {
        return offset - 1;
    }

    private boolean checkForWinIn(Cell[][] rows) {
        for (Cell[] row : rows) {
            if (containsOnly(X, row)
                    || containsOnly(O, row)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsOnly(PlayerSymbol symbol, Cell[] row) {
        return Arrays.equals(getTheSymbolsFromCells().apply(row), new PlayerSymbol[]{symbol, symbol, symbol});
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

    private Cell[][] getLinesFromGrid() {
        LineGenerator lineGenerator = new LineGenerator(grid);
        return lineGenerator.linesForAllDirections();
    }

    private boolean isWithinGridBoundary(int offset) {
        return offset >= 0 && offset < NUMBER_OF_SLOTS;
    }

    private boolean isVacantAt(int cellIndex) {
        return grid[cellIndex].getSymbol() == VACANT;
    }
}