package ttt;

import java.util.Arrays;

import static ttt.PlayerSymbol.*;
import static ttt.PlayerSymbol.VACANT;

public class Board {
    private PlayerSymbol[] grid = new PlayerSymbol[BOARD_DIMENSION * BOARD_DIMENSION];

    protected static final int BOARD_DIMENSION = 3;
    private static final int NUMBER_OF_SLOTS = BOARD_DIMENSION * BOARD_DIMENSION;

    public Board() {
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            grid[i] = VACANT;
        }
    }

    public Board(PlayerSymbol... grid) {
        this.grid = grid;
    }

    public boolean hasWinningCombination() {
        return hasWinningRow() || hasWinningColumn() || hasWinningDiagonal();
    }

    public PlayerSymbol getSymbolAt(int index) {
        return grid[index];
    }

    public boolean hasFreeSpace() {
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            if (isVacantAt(i)) {
                return true;
            }
        }
        return false;
    }

    public void updateAt(int move, PlayerSymbol symbol) {
        grid[move] = symbol;
    }

    public boolean isValidPositionAt(int index) {
        return isWithinGridBoundary(index) && isVacantAt(index);
    }

    private boolean hasWinningRow() {
        PlayerSymbol[] topRow = new PlayerSymbol[]{grid[0], grid[1], grid[2]};
        PlayerSymbol[] middleRow = new PlayerSymbol[]{grid[3], grid[4], grid[5]};
        PlayerSymbol[] bottomRow = new PlayerSymbol[]{grid[6], grid[7], grid[8]};

        PlayerSymbol[][] horizontalRows = new PlayerSymbol[][]{topRow, middleRow, bottomRow};

        return checkForWinIn(horizontalRows);
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
        PlayerSymbol[] leftColumn = new PlayerSymbol[]{grid[0], grid[3], grid[6]};
        PlayerSymbol[] middleColumn = new PlayerSymbol[]{grid[1], grid[4], grid[7]};
        PlayerSymbol[] rightColumn = new PlayerSymbol[]{grid[2], grid[5], grid[8]};

        PlayerSymbol[][] columns = new PlayerSymbol[][]{leftColumn, middleColumn, rightColumn};

        return checkForWinIn(columns);
    }

    private boolean hasWinningDiagonal() {
        PlayerSymbol[] backslashDiagonal = new PlayerSymbol[]{grid[0], grid[4], grid[8]};
        PlayerSymbol[] forwardslashDiagonal = new PlayerSymbol[]{grid[2], grid[4], grid[6]};

        PlayerSymbol[][] diagonalRows = new PlayerSymbol[][]{backslashDiagonal, forwardslashDiagonal};
        return checkForWinIn(diagonalRows);
    }

    private boolean isVacantAt(int index) {
        return grid[index] == VACANT;
    }

    private boolean isWithinGridBoundary(int index) {
        return index >= 0 && index < NUMBER_OF_SLOTS;
    }
}
