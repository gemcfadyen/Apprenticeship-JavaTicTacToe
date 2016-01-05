package ttt.game.board;

import ttt.commandlineapp.BoardDisplay;

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
