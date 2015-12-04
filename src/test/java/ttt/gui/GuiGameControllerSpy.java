package ttt.gui;

import ttt.GameType;

public class GuiGameControllerSpy implements GameController {
    private boolean hasPresentedBoardDimensions = false;
    private GameType gameType;
    private boolean hasPresentedBoard = false;
    private int boardDimension = 0;

    @Override
    public void presentGameTypes() {

    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.gameType = gameType;
        hasPresentedBoardDimensions = true;
    }

    @Override
    public void presentBoard(String dimensionForBoard) {
        hasPresentedBoard = true;
        boardDimension = Integer.valueOf(dimensionForBoard);
    }

    public boolean hasPresentedBoardDimensions() {
        return hasPresentedBoardDimensions;
    }

    public GameType capturedGameType() {
        return gameType;
    }

    public boolean hasPresentedBoard() {
        return hasPresentedBoard;
    }

    public int boardSize() {
        return boardDimension;
    }
}
