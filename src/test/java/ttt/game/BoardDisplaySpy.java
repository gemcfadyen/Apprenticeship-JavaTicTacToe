package ttt.game;

import ttt.game.Board;
import ttt.game.BoardDisplay;

public class BoardDisplaySpy implements BoardDisplay {
    private int numberOfBoards = 0;

    @Override
    public String formatForDisplay(Board board) {
       numberOfBoards++;
        return "";
    }

    public int numberOfTimesBoardDisplayed() {
        return numberOfBoards;
    }
}
