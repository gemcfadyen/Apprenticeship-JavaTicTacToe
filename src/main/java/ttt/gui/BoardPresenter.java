package ttt.gui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface BoardPresenter {
    void presentGameTypes(String gameType);
    void presentGridDimensionsFor(String dimension);
    void presentsBoard(Board board);
    void printsWinningMessage(Board board, PlayerSymbol symbol);
    void printsDrawMessage(Board board);
}
