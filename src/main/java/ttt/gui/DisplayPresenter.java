package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import java.util.List;

public interface DisplayPresenter {
    void presentGameTypes(List<GameType> allGameTypes);
    void presentGridDimensionsBetween(int lowerBoundary, int upperBoundary);
    void presentsBoard(Board board);
    void printsWinningMessage(Board board, PlayerSymbol symbol);
    void printsDrawMessage(Board board);
    void presentReplayOption();
}
