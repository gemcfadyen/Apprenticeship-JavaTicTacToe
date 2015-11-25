package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GameControllerSpy implements Prompt {
    private boolean promptedForGameDimension = false;

    @Override
    public void presentGameTypes() {

    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        promptedForGameDimension = true;
    }

    @Override
    public int readBoardDimension(GameType gameType) {
        return 3;
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
    public void print(Board board) {

    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void printDrawMessage() {
    }

    public boolean hasPresentedGridDimensions() {
        return promptedForGameDimension;
    }
}
