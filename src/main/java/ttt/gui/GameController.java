package ttt.gui;

import ttt.GameType;

public interface GameController {
    void presentGameTypes();
    void presentBoardDimensionsFor(GameType gameType);
    void presentBoard(int dimension);
    void playMove(int position);
}
