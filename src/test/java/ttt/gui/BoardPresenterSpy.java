package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public class BoardPresenterSpy implements BoardPresenter {
    private boolean hasPresentedGridDimension = false;
    private boolean hasPresentedGameTypes;
    private boolean hasDrawnBoard = false;
    private boolean hasPrintedWinningBoard = false;
    private boolean hasPrintedGameIsDrawn = false;

    @Override
    public void presentGameTypes(String gameType) {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentGridDimensionsFor(GameType gameType) {
        hasPresentedGridDimension = true;
    }

    @Override
    public void presentsBoard(Board board) {
        hasDrawnBoard = true;
    }

    @Override
    public void printsWinning(Board board, PlayerSymbol symbol) {
        hasPrintedWinningBoard = true;
    }

    @Override
    public void printsDraw(Board board) {
        hasPrintedGameIsDrawn = true;
    }

    public boolean hasPresentedGameTypes() {
        return hasPresentedGameTypes;
    }

    public boolean hasPresentedGridDimensions() {
        return hasPresentedGridDimension;
    }

    public boolean hasDrawnBoard() {
        return hasDrawnBoard;
    }

    public boolean hasIdentifiedAWin() {
        return hasPrintedWinningBoard;
    }

    public boolean hasIdentifiedADraw() {
        return hasPrintedGameIsDrawn;
    }
}
