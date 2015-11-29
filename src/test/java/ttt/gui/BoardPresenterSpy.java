package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

public class BoardPresenterSpy implements BoardPresenter {
    private boolean hasPresentedGridDimension = false;
    private boolean hasPresentedGameTypes;
    private boolean hasDrawnBoard = false;

    @Override
    public void presentGameTypes() {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentGridDimensionsFor(GameType gameType) {
        hasPresentedGridDimension = true;
    }

    @Override
    public void presentsBoard(Board board) {
        hasDrawnBoard = true;
    }

    public boolean hasPresentedGameTypes() {
        return hasPresentedGameTypes;
    }

    public boolean hasPresentedGridDimensions() {
        return hasPresentedGridDimension;
    }

    public boolean hasDrawnBoard() {
        return hasDrawnBoard;
    }
}
