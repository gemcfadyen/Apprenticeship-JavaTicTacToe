package ttt.ui;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.List;

public class PlainFormatter implements DisplayFormatter {
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
    public String formatBoardDimensionMessage(int lowerDimension, int largestDimension) {
        return "Please enter the dimension of the board you would like to use [" + lowerDimension + " to " + largestDimension + "]";
    }

    @Override
    public String applyFontColour(String message) {
        return message;
    }

    @Override
    public String applyInvalidColour(String message) {
        return message;
    }
}
