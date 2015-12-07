package ttt.ui;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.List;

public class PlainFormatter implements BoardFormatter {
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

    @Override
    public String formatWinningMessage(PlayerSymbol symbol) {
        return "Congratulations - " + symbol.name() + " has won";
    }

    @Override
    public String formatPlayAgainMessage() {
        return "Play again? [Y/N]";
    }

    @Override
    public String formatDrawMessage() {
        return "No winner this time";
    }


}
