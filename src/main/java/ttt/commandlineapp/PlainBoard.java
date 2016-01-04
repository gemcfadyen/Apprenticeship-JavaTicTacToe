package ttt.commandlineapp;

import ttt.game.Board;
import ttt.game.Line;
import ttt.game.BoardDisplay;
import ttt.game.PlayerSymbol;

import java.util.List;

public class PlainBoard implements BoardDisplay {
    @Override
    public String formatForDisplay(Board board) {
        String plainBoard = "";

        List<Line> rows = board.getRows();
        for (Line row : rows) {
            PlayerSymbol[] symbols = row.getSymbols();
            for (PlayerSymbol symbol : symbols) {
                plainBoard+=symbol.getSymbolForDisplay();
            }
        }
        return plainBoard;
    }

}
