package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;
import ttt.ui.WritePromptForGui;

public class GuiPromptSpy implements WritePromptForGui {
    private boolean promptedForGameDimension = false;
    private int numberOfTimesBoardIsPrinted = 0;
    private GameType chosenGameType;
    private int dimension;
    private int numberOfTimesWinPrinted = 0;
    private int numberOfTimesDrawPrinted = 0;

    @Override
    public void presentGameTypes() {
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.chosenGameType = gameType;
        promptedForGameDimension = true;
    }

    @Override
    public void printBoard() {
//        this.dimension = board.getRows().size();
        numberOfTimesBoardIsPrinted++;
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        numberOfTimesWinPrinted++;
    }

    @Override
    public void printDrawMessage() {
        numberOfTimesDrawPrinted++;
    }

    public boolean hasPresentedGridDimensions() {
        return promptedForGameDimension;
    }

    public int getNumberOfTimesBoardIsPrinted() {
        return numberOfTimesBoardIsPrinted;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean hasPromptedForGameDimensionFor(String gameType) {
        return gameType.equals(chosenGameType.gameNameForDisplay());
    }

    public int numberOfTimesWinPrinted() {
        return numberOfTimesWinPrinted;
    }

    public int numberOfTimesDrawPrinted() {
        return numberOfTimesDrawPrinted;
    }

}
