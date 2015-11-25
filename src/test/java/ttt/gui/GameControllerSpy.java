package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GameControllerSpy implements Prompt, WritePrompt {
    private boolean promptedForGameDimension = false;
    private int numberOfTimesPromptHasBeenCalled = 0;

    public boolean hasPresentedGridDimensions() {
        return promptedForGameDimension;
    }

    public int getNumberOfTimesPromptHasBeenCalled() {
        return numberOfTimesPromptHasBeenCalled;
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        promptedForGameDimension = true;
        numberOfTimesPromptHasBeenCalled++;
    }

    @Override
    public int getBoardDimension(GameType gameType) {
        return 3;
    }

    @Override
    public GameType getGameType() {
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
}
