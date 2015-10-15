package ttt;

import static ttt.PlayerSymbol.VACANT;

public class Board {
    private PlayerSymbol[] grid = new PlayerSymbol[BOARD_DIMENSION * BOARD_DIMENSION];

    protected static final int BOARD_DIMENSION = 3;
    protected static final int NUMBER_OF_SLOTS = BOARD_DIMENSION * BOARD_DIMENSION;

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

    public PlayerSymbol getSymbolAt(Integer index) {
        return grid[index];
    }

    public boolean hasFreeSpace() {
        for (PlayerSymbol symbol : grid) {
            if (vacant(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningRow() {
        for (int i = 0; i < NUMBER_OF_SLOTS; i = i + BOARD_DIMENSION) {
            if (hasMatchingSymbolsBetween(i, i + BOARD_DIMENSION)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMatchingSymbolsBetween(int startingIndex, int finishingIndex) {
        boolean isSameSymbol = true;
        PlayerSymbol symbol = grid[startingIndex];

        if (vacant(symbol)) return false;

        for (int i = startingIndex; i < finishingIndex; i++) {
            isSameSymbol = isSameSymbol && grid[i].equals(symbol);
        }

        return isSameSymbol;
    }

    private boolean vacant(PlayerSymbol symbol) {
        return symbol.equals(VACANT);
    }

    private boolean hasWinningColumn() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            PlayerSymbol symbol = grid[i];
            if (vacant(symbol)) {
                continue;
            }

            if (grid[i].equals(symbol)
                    && grid[i + BOARD_DIMENSION].equals(symbol)
                    && grid[i + (2 * BOARD_DIMENSION)].equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningDiagonal() {
        return checksDiagonalAt(0, BOARD_DIMENSION + 1, NUMBER_OF_SLOTS - 1) ||
                checksDiagonalAt(BOARD_DIMENSION - 1, BOARD_DIMENSION + 1, 2 * BOARD_DIMENSION);
    }

    private boolean checksDiagonalAt(int topRowIndex, int middleRowIndex, int bottomRowIndex) {
        PlayerSymbol symbol = grid[topRowIndex];
        if (vacant(symbol)) {
            return false;
        }

        if (grid[middleRowIndex].equals(symbol)
                && grid[bottomRowIndex].equals(symbol)) {
            return true;
        }
        return false;
    }
}
