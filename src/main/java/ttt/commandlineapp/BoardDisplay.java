package ttt.commandlineapp;

import ttt.game.board.Board;

public interface BoardDisplay {
    String formatForDisplay(Board board);
}
