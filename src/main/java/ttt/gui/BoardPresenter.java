package ttt.gui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface BoardPresenter {
    void presentGameTypes(String gameType);
    void presentGridDimensionsFor(String dimension);
    void presentsBoard(Board board);
    void printsWinning(Board board, PlayerSymbol symbol);
    void printsDraw(Board board);
}
