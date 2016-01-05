package ttt.game;

import ttt.game.board.Board;

import java.util.List;

public interface DisplayPresenter {
    void presentGameTypes(List<GameType> allGameTypes);
    void presentGridDimensionsBetween(int lowerBoundary, int upperBoundary);
    void presentsBoard(Board board);
    void printsWinningMessage(Board board, PlayerSymbol symbol);
    void printsDrawMessage(Board board);
    void presentReplayOption();
}
