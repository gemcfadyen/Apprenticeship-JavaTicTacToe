package ttt.gui;

import ttt.GameType;

public class GuiGameControllerSpy implements GameController {
    private boolean hasPresentedBoardDimensions = false;
    private GameType gameType;

    @Override
    public void presentGameTypes() {

    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.gameType = gameType;
        hasPresentedBoardDimensions = true;
    }

    public boolean hasPresentedBoardDimensions() {
        return hasPresentedBoardDimensions;
    }

    public GameType capturedGameType() {
        return gameType;
    }
}
