package ttt.gui;

import ttt.GameType;

public class BoardPresenterSpy implements BoardPresenter {
    private boolean hasPresentedGridDimension = false;
    private boolean hasPresentedGameTypes;

    public boolean hasPresentedGridDimensions() {
        return hasPresentedGridDimension;
    }

    @Override
    public void presentGameTypes() {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentGridDimensionsFor(GameType gameType) {
        hasPresentedGridDimension = true;
    }

    public boolean hasPresentedGameTypes() {
        return hasPresentedGameTypes;
    }
}
