package ttt.gui;

import ttt.GameType;

public interface BoardPresenter {
    void presentGameTypes();
    void presentGridDimensionsFor(GameType gameType);
}
