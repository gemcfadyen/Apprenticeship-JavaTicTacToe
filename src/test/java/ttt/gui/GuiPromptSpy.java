package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class GuiPromptSpy implements GameRulesPrompt {
    private boolean promptedForGameDimension = false;
    private int numberOfTimesBoardIsPrinted = 0;
    private GameType chosenGameType;
    private int dimension;
    private boolean gameIsStarted = false;
    private int move;

    @Override
    public void presentGameTypes() {
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.chosenGameType = gameType;
        promptedForGameDimension = true;
    }

    @Override
    public void print(Board board) {
        this.dimension = board.getRows().size();
        numberOfTimesBoardIsPrinted++;
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
    }

    @Override
    public void printDrawMessage() {
    }

    @Override
    public void playMoveAt(String move) {
        gameIsStarted = true;
        this.move = Integer.valueOf(move);
    }

    @Override
    public PlayerSymbol getCurrentPlayer() {
        return X;
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

    public boolean gameIsStarted() {
        return gameIsStarted;
    }

    public int moveTaken() {
        return move;
    }
}
