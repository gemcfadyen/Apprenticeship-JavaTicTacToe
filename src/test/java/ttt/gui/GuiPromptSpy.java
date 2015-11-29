package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GuiPromptSpy implements Prompt {
    private boolean promptedForGameDimension = false;
    private int numberOfTimesBoardIsPrinted = 0;
    private boolean promptedForGameType = false;
    private GameType chosenGameType;

    @Override
    public void presentGameTypes() {
        promptedForGameType = true;
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.chosenGameType = gameType;
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
        numberOfTimesBoardIsPrinted++;
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

    public int getNumberOfTimesBoardIsPrinted() {
        return numberOfTimesBoardIsPrinted;
    }

    public boolean hasPresentedGameTypes() {
        return promptedForGameType;
    }

    public boolean hasPromptedForGameDimensionFor(String gameType) {
        if (gameType.equals(chosenGameType.gameNameForDisplay())) {
            return true;
        }

        return false;
    }
}
