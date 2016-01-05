package ttt.commandlineapp.formatting;

import ttt.commandlineapp.BoardDisplay;
import ttt.game.board.Board;
import ttt.game.board.Line;
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
