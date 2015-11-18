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
        return Arrays.equals(line, matching(symbol));
    }

    public PlayerSymbol[] getSymbols() {
        return line;
    }

    private PlayerSymbol[] matching(PlayerSymbol symbol) {
        PlayerSymbol[] lineOfMatchingSymbols = new PlayerSymbol[line.length];
        for(int i=0; i<line.length; i++) {
            lineOfMatchingSymbols[i] = symbol;
        }
        return lineOfMatchingSymbols;
    }
}