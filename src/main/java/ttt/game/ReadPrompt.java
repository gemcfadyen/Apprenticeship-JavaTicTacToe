package ttt.game;

import ttt.game.board.Board;

import java.util.List;

public interface ReadPrompt {
    int readBoardDimension(int lowerDimension, int largestDimension);
    GameType readGameType(List<GameType> gameTypes);
    ReplayOption readReplayOption();
    int readNextMove(Board board);
}
