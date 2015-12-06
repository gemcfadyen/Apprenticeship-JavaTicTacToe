package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.util.List;

public class UnusedPrompt implements Prompt {
    @Override
    public void presentGameTypes(List<GameType> allGameTypes) {

    }

    @Override
    public void presentGridDimensionsUpTo(String dimension) {

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
    public int readBoardDimension(int largestDimension) {
        return 0;
    }

    @Override
    public GameType readGameType() {
        return null;
    }

    @Override
    public ReplayOption getReplayOption() {
        return null;
    }

    @Override
    public int getNextMove(Board board) {
        return 0;
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {

    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void print(Board board) {

    }

    @Override
    public void printDrawMessage() {

    }
}
