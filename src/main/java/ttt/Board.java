package ttt;

import static ttt.PlayerSymbol.VACANT;

/**
 * Created by Georgina on 09/10/15.
 */
public class Board {
    private static final int BOARD_DIMENSION = 3;
    PlayerSymbol[] symbols = new PlayerSymbol[BOARD_DIMENSION * BOARD_DIMENSION];

    public Board() {
        for (int i = 0; i < BOARD_DIMENSION * BOARD_DIMENSION; i++) {
            symbols[i] = VACANT;
        }
    }

    public Board(PlayerSymbol... symbols) {
        this.symbols = symbols;
    }


    public boolean hasWinningCombination() {
        if (hasWinningRow() || hasWinningColumn() || hasWinningDiagonal()) {
            return true;
        }

        return false;
    }

    public void updateAt(int index, PlayerSymbol symbol) {
        symbols[index] = symbol;
    }

    public PlayerSymbol getSymbolAt(Integer index) {
        return symbols[index];
    }

    public boolean hasFreeSpace() {
        for (PlayerSymbol symbol : symbols) {
            if (vacant(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningRow() {
        for (int i = 0; i < BOARD_DIMENSION * BOARD_DIMENSION; i = i + BOARD_DIMENSION) {
            if (hasMatchingSymbolsBetween(i, i + BOARD_DIMENSION)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMatchingSymbolsBetween(int startingIndex, int finishingIndex) {
        boolean isSameSymbol = true;
        PlayerSymbol symbol = symbols[startingIndex];

        if (vacant(symbol)) return false;

        for (int i = startingIndex; i < finishingIndex; i++) {
            isSameSymbol = isSameSymbol && symbols[i].equals(symbol);
        }

        return isSameSymbol;
    }

    private boolean vacant(PlayerSymbol symbol) {
        return symbol.equals(VACANT);
    }

    private boolean hasWinningColumn() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            PlayerSymbol symbol = symbols[i];
            if (vacant(symbol)) {
                continue;
            }

            if (symbols[i].equals(symbol)
                    && symbols[i + BOARD_DIMENSION].equals(symbol)
                    && symbols[i + (2 * BOARD_DIMENSION)].equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningDiagonal() {
        return checksDiagonalAt(0, BOARD_DIMENSION + 1, BOARD_DIMENSION * BOARD_DIMENSION - 1) ||
                checksDiagonalAt(BOARD_DIMENSION - 1, BOARD_DIMENSION + 1, 2 * (BOARD_DIMENSION));
    }


    private boolean checksDiagonalAt(int topRowIndex, int middleRowIndex, int bottomRowIndex) {
        PlayerSymbol symbol = symbols[topRowIndex];
        if (vacant(symbol)) {
            return false;
        }

        if (symbols[middleRowIndex].equals(symbol)
                && symbols[bottomRowIndex].equals(symbol)) {
            return true;
        } else {
            return false;
        }
    }
}
