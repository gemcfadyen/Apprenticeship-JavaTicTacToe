package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Board {
    String[] symbols = new String[9];

    public Board() {
        for (int i = 0; i < 9; i++) {
            symbols[i] = "-";
        }
    }

    public Board(String... symbols) {
        this.symbols = symbols;
    }

    public boolean containsWinningRow() {
        if (hasMatchingSymbolsBetween(0, 3)) {
            return true;
        } else if (hasMatchingSymbolsBetween(3, 6)) {
            return true;
        } else if (hasMatchingSymbolsBetween(6, 9)) {
            return true;
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
