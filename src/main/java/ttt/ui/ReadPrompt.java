package ttt.ui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;

import java.util.List;

public interface ReadPrompt {
    int readBoardDimension(int largestDimension);
    GameType readGameType(List<GameType> gameTypes);
    ReplayOption getReplayOption();
    int getNextMove(Board board);
}
