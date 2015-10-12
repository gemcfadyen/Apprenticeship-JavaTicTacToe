package ttt;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayer {
    private final String symbol;

    public HumanPlayer(String symbol) {
        this.symbol = symbol;
    }

    public int chooseNextMoveFrom(Board board) {
        return 2;
    }

    public String getSymbols() {
        return symbol;
    }
}
