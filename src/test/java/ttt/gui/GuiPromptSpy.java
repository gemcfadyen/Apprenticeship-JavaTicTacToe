package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class GuiPromptSpy implements TemporaryGuiPrompt {
    private boolean promptedForGameDimension = false;
    private int numberOfTimesBoardIsPrinted = 0;
    private boolean promptedForGameType = false;
    private GameType chosenGameType;
    private int dimension;
    private boolean gameIsStarted = false;
    private int move;

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
        this.dimension = board.getRows().size();
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

    public int getDimension() {
        return dimension;
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

    public boolean gameIsStarted() {
        return gameIsStarted;
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

    public int moveTaken() {
        return move;
    }
}
