package ttt.gui;

import ttt.GameType;

public interface TTTController {

    void presentBoardDimensionsFor(GameType gameType);

    void presentBoard(String dimensionForBoard);

    void playMove(String id);
}
