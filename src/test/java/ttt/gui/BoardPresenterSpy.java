package ttt.gui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public class BoardPresenterSpy implements BoardPresenter {
    private boolean hasPresentedGridDimension = false;
    private boolean hasPresentedGameTypes;
    private boolean hasDisplayedBoard = false;
    private boolean hasPrintedWinningBoard = false;
    private boolean hasPrintedGameIsDrawn = false;
    private PlayerSymbol winningSymbol;

    @Override
    public void presentGameTypes(String gameType) {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentGridDimensionsFor(String dimension) {
        hasPresentedGridDimension = true;
    }

    @Override
    public void presentsBoard(Board board) {
        hasDisplayedBoard = true;
    }

    @Override
    public void printsWinning(Board board, PlayerSymbol symbol) {
        winningSymbol = symbol;
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
        return hasDisplayedBoard;
    }

    public boolean hasIdentifiedAWin() {
        return hasPrintedWinningBoard;
    }

    public boolean hasIdentifiedADraw() {
        return hasPrintedGameIsDrawn;
    }

    public PlayerSymbol getWinningSymbol() {
        return winningSymbol;
    }
}
