package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import java.util.List;

public class DisplayPresenterSpy implements DisplayPresenter {
    private boolean hasPresentedGridDimension = false;
    private boolean hasPresentedGameTypes;
    private boolean hasDisplayedBoard = false;
    private boolean hasPrintedWinningBoard = false;
    private boolean hasPrintedGameIsDrawn = false;
    private boolean hasAskedForReplay = false;
    private PlayerSymbol winningSymbol;

    @Override
    public void presentGameTypes(List<GameType> gameTypes) {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentGridDimensionsUpTo(String dimension) {
        hasPresentedGridDimension = true;
    }

    @Override
    public void presentsBoard(Board board) {
        hasDisplayedBoard = true;
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
        winningSymbol = symbol;
        hasPrintedWinningBoard = true;
    }

    @Override
    public void printsDrawMessage(Board board) {
        hasPrintedGameIsDrawn = true;
    }

    @Override
    public void presentReplayOption() {
        hasAskedForReplay = true;
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

    public boolean hasAskedForReplay() {
        return hasAskedForReplay;
    }
}
