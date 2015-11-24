package ttt.ui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;

public interface ReadPrompt {
    int readBoardDimension(GameType gameType);

    GameType readGameType();

    ReplayOption getReplayOption();

    int getNextMove(Board board);
}