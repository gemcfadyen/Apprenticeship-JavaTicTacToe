package ttt.ui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface BoardFormatter {
    String formatForDisplay(Board board);

    String formatWinningMessage(PlayerSymbol symbol);

    String formatBoardDimensionMessage(int largestDimension);

    String applyFontColour(String message);

    String applyInvalidColour(String message);
}
