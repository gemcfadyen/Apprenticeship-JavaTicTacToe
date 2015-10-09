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
        if (symbols[0] == "X"
                && symbols[1] == "X"
                && symbols[2] == "X") {
            return true;
        } else if (symbols[6] == "X"
                && symbols[7] == "X"
                && symbols[8] == "X") {
            return true;
        } else if (symbols[3] == "X"
                && symbols[4] == "X"
                && symbols[5] == "X") {
            return true;
        }

        return false;
    }

}
