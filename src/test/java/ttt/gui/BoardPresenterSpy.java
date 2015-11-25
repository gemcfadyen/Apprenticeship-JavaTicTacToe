package ttt.gui;

import ttt.GameType;

public class BoardPresenterSpy implements BoardPresenter {
    private boolean hasPresentedGridDimension = false;

    public boolean hasPresentedGridDimensions() {
        return hasPresentedGridDimension;
    }

    @Override
    public void presentGameTypes() {

    }

    @Override
    public void presentGridDimensionsFor(GameType gameType) {
        hasPresentedGridDimension = true;
    }
}
