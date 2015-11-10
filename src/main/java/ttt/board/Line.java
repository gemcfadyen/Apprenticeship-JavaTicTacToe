package ttt.board;

import ttt.player.PlayerSymbol;

import java.util.Arrays;

import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class Line {
    private PlayerSymbol[] line;

    public Line(PlayerSymbol... symbols) {
        line = symbols;
    }

    public boolean isWinning() {
        return containsOnly(X) || containsOnly(O);
    }

    private boolean containsOnly(PlayerSymbol symbol) {
        return Arrays.equals(line, new PlayerSymbol[]{symbol, symbol, symbol});
    }

    public PlayerSymbol[] getSymbols() {
        return line;
    }
}