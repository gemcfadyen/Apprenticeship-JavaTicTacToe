package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Board {
    private static final int BOARD_DIMENSION = 3;
    String[] symbols = new String[BOARD_DIMENSION * BOARD_DIMENSION];

    public Board() {
        for (int i = 0; i < BOARD_DIMENSION * BOARD_DIMENSION; i++) {
            symbols[i] = "-";
        }
    }

    public Board(String... symbols) {
        this.symbols = symbols;
    }

    public boolean hasWinningCombination() {
        if (hasWinningRow() || hasWinningColumn() || hasWinningDiagonal()) {
            return true;
        }

        return false;
    }

    private boolean hasWinningDiagonal() {
        return checksDiagonalAt(0, BOARD_DIMENSION + 1, BOARD_DIMENSION * BOARD_DIMENSION - 1) ||
                checksDiagonalAt(BOARD_DIMENSION - 1, BOARD_DIMENSION + 1, 2 * (BOARD_DIMENSION));
    }

    private boolean checksDiagonalAt(int topRowIndex, int middleRowIndex, int bottomRowIndex) {
        String symbol = symbols[topRowIndex];
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

    private boolean hasWinningColumn() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            String symbol = symbols[i];
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

    private boolean hasMatchingSymbolsBetween(int startingIndex, int finishingIndex) {
        boolean isSameSymbol = true;
        String symbol = symbols[startingIndex];

        if (vacant(symbol)) return false;

        for (int i = startingIndex; i < finishingIndex; i++) {
            isSameSymbol = isSameSymbol && symbols[i].equals(symbol);
        }

        return isSameSymbol;
    }

    private boolean hasWinningRow() {
        for (int i = 0; i < BOARD_DIMENSION * BOARD_DIMENSION; i = i + BOARD_DIMENSION) {
            if (hasMatchingSymbolsBetween(i, i + BOARD_DIMENSION)) {
                return true;
            }
        }
        return false;
    }

    private boolean vacant(String symbol) {
        return symbol.equals("-");
    }
}
