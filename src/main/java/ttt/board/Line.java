package ttt.board;

import ttt.player.PlayerSymbol;

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

    private boolean containsOnly(PlayerSymbol playerSymbol) {
        boolean allMatching = true;
        for(PlayerSymbol symbol : line) {
            allMatching = allMatching && (symbol == playerSymbol);
        }

        return allMatching;

    }

    public PlayerSymbol[] getSymbols() {
        return line;
    }
}