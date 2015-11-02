package ttt;

import java.util.Arrays;
import java.util.function.Function;

import static ttt.PlayerSymbol.*;

public class Board {
    protected static final int BOARD_DIMENSION = 3;
    private static final int NUMBER_OF_SLOTS = BOARD_DIMENSION * BOARD_DIMENSION;
    private Cell[] grid = new Cell[BOARD_DIMENSION * BOARD_DIMENSION];

    public Board() {
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            grid[i] = new Cell(i + 1, VACANT);
        }
    }

    public Board(PlayerSymbol... grid) {
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            this.grid[i] = new Cell(i + 1, grid[i]);
        }
    }

    public boolean hasWinningCombination() {
        return hasWinningRow() || hasWinningColumn() || hasWinningDiagonal();
    }

    public PlayerSymbol getSymbolAt(int offset) {
        return grid[offset - 1].getSymbol();
    }

    public boolean hasFreeSpace() {
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            if (isVacantAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public void updateAt(int offset, PlayerSymbol symbol) {
        grid[offset - 1].setSymbol(symbol);
    }

    public boolean isValidPositionAt(int offset) {
        return isWithinGridBoundary(offset) && isVacantAt(offset);
    }

    private boolean hasWinningRow() {
        Cell[][] rows = getRows();

        PlayerSymbol[][] horizontalRows = new PlayerSymbol[rows.length][rows.length];
        for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
            horizontalRows[rowIndex] = getTheSymbolsFromCells().apply(rows[rowIndex]);
        }

        return checkForWinIn(horizontalRows);
    }

    public Cell[][] getRows() {
        Cell[] topRow = new Cell[]{grid[0], grid[1], grid[2]};
        Cell[] middleRow = new Cell[]{grid[3], grid[4], grid[5]};
        Cell[] bottomRow = new Cell[]{grid[6], grid[7], grid[8]};

        return new Cell[][]{topRow, middleRow, bottomRow};
    }

    private Function<Cell[], PlayerSymbol[]> getTheSymbolsFromCells() {
        return cells -> {
            PlayerSymbol[] symbols = new PlayerSymbol[cells.length];
            for (int i = 0; i < cells.length; i++) {
                symbols[i] = cells[i].getSymbol();
            }
            return symbols;
        };
    }

    private boolean checkForWinIn(PlayerSymbol[][] rows) {
        for (PlayerSymbol[] horizontalRow : rows) {
            if (Arrays.equals(horizontalRow, new PlayerSymbol[]{X, X, X})
                    || Arrays.equals(horizontalRow, new PlayerSymbol[]{O, O, O})) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningColumn() {
        PlayerSymbol[] leftColumn = new PlayerSymbol[]{grid[0].getSymbol(), grid[3].getSymbol(), grid[6].getSymbol()};
        PlayerSymbol[] middleColumn = new PlayerSymbol[]{grid[1].getSymbol(), grid[4].getSymbol(), grid[7].getSymbol()};
        PlayerSymbol[] rightColumn = new PlayerSymbol[]{grid[2].getSymbol(), grid[5].getSymbol(), grid[8].getSymbol()};

        PlayerSymbol[][] columns = new PlayerSymbol[][]{leftColumn, middleColumn, rightColumn};

        return checkForWinIn(columns);
    }

    private boolean hasWinningDiagonal() {
        PlayerSymbol[] backslashDiagonal = new PlayerSymbol[]{grid[0].getSymbol(), grid[4].getSymbol(), grid[8].getSymbol()};
        PlayerSymbol[] forwardslashDiagonal = new PlayerSymbol[]{grid[2].getSymbol(), grid[4].getSymbol(), grid[6].getSymbol()};

        PlayerSymbol[][] diagonalRows = new PlayerSymbol[][]{backslashDiagonal, forwardslashDiagonal};
        return checkForWinIn(diagonalRows);
    }

    private boolean isVacantAt(int offset) {
        return grid[offset - 1].getSymbol() == VACANT;
    }

    private boolean isWithinGridBoundary(int offset) {
        return offset > 0 && offset <= NUMBER_OF_SLOTS;
    }

}