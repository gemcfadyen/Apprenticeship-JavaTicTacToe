package ttt.game;

import ttt.game.board.Board;

import java.util.List;

public class UnusedPrompt implements ReadPrompt {

    @Override
    public int readBoardDimension(int lowerDimension, int largestDimension) {
        return 0;
    }

    @Override
    public GameType readGameType(List<GameType> gameTypes) {
        return null;
    }

    @Override
    public ReplayOption readReplayOption() {
        return null;
    }

    @Override
    public int readNextMove(Board board) {
        return 0;
    }

}
