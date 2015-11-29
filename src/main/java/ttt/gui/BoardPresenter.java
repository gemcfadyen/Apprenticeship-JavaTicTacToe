package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

public interface BoardPresenter {
    void presentGameTypes();
    void presentGridDimensionsFor(GameType gameType);
    void presentsBoard(Board board);
}
