package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Board {
    private static final int BOARD_DIMENSION = 3;
    String[] symbols = new String[9];

    public Board() {
        for (int i = 0; i < 9; i++) {
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
        return checkBackslashDiagonal() || checkForwardslashDiagonal();
    }

    private boolean checkForwardslashDiagonal() {
        String symbol = symbols[2];
        if (vacant(symbol)) {
            return false;
        }
        if (symbols[4].equals(symbol)
                && symbols[6].equals(symbol)) {
            return true;
        }
        return false;
    }

    private boolean checkBackslashDiagonal() {
        String symbol = symbols[0];
        if (vacant(symbol)) {
            return false;
        }

        if (symbols[4].equals(symbol)
                && symbols[8].equals(symbol)) {
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
