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
        if (hasWinningRow()) {
            return true;
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
        String symbol = symbols[startingIndex];

        if (vacant(symbol)) return false;

        for (int i = startingIndex; i < finishingIndex; i++) {
            isSameSymbol = isSameSymbol && symbols[i].equals(symbol);
        }

        return isSameSymbol;
    }

    private boolean vacant(String symbol) {
        return symbol.equals("-");
    }
}
