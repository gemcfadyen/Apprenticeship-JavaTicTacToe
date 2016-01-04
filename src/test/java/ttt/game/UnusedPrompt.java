package ttt.game;

import ttt.game.GameType;
import ttt.game.ReplayOption;
import ttt.game.Board;
import ttt.game.PlayerSymbol;
import ttt.commandlineapp.Prompt;

import java.util.List;

public class UnusedPrompt implements Prompt {
    @Override
    public void presentGameTypes(List<GameType> allGameTypes) {
    }

    @Override
    public void presentGridDimensionsBetween(int lower, int upper) {
    }

    @Override
    public void presentsBoard(Board board) {
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
    }

    @Override
    public void printsDrawMessage(Board board) {
    }

    @Override
    public void presentReplayOption() {
    }

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
