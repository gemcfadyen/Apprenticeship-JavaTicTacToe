package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Board {
    String[] symbols = new String[9];

    public Board(String... symbols) {
        this.symbols = symbols;
    }

    public boolean containsWinningRow() {
        if (symbols[0] == "X"
                && symbols[1] == "X"
                && symbols[2] == "X") {
            return true;
        }

        return false;
    }
}
