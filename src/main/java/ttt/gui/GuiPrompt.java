package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.WritePrompt;

import static ttt.player.PlayerSymbol.X;

public class GuiPrompt implements WritePrompt {
    private BoardPresenter boardPresenter;
    private Board board;
    private PlayerSymbol currentPlayer;

    public GuiPrompt(BoardPresenter boardPresenter) {
        this.boardPresenter = boardPresenter;
        this.currentPlayer = X;
    }

    GuiPrompt(BoardPresenter boardPresenter, Board board) {
        this(boardPresenter);
        this.board = board;
    }

    @Override
    public void print(Board board) {
        this.board = board;
        boardPresenter.presentsBoard(board);
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        boardPresenter.printsWinning(board, symbol);
    }

    @Override
    public void printDrawMessage() {
        boardPresenter.printsDraw(board);
    }

    @Override
    public void presentGameTypes() {
        boardPresenter.presentGameTypes();
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        boardPresenter.presentGridDimensionsFor(gameType);
    }
}
