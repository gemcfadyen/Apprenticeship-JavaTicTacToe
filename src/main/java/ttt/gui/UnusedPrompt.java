package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.util.List;

public class UnusedPrompt implements Prompt {
    @Override
    public void presentGameTypes(List<GameType> allGameTypes) {
    }

    @Override
    public void presentGridDimensionsBetween(int lower, int upper) {
    }

    @Override
    public void presentsBoard(Board board, Player currentPlayer) {
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
