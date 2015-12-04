package ttt.gui;

import ttt.GameType;

public interface GameController {
    void presentGameTypes();
    void presentBoardDimensionsFor(GameType gameType);
}
