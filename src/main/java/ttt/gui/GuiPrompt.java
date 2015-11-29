package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public class GuiPrompt implements TemporaryGuiPrompt {
    private BoardPresenter boardPresenter;
    private Board board;
    private PlayerSymbol currentPlayer;

    public GuiPrompt(BoardPresenter boardPresenter) {
        this.boardPresenter = boardPresenter;
        this.currentPlayer = PlayerSymbol.X;
    }

    GuiPrompt(BoardPresenter boardPresenter, Board board) {
        this.boardPresenter = boardPresenter;
        this.board = board;
        this.currentPlayer = PlayerSymbol.X;
    }

    @Override
    public int readBoardDimension(GameType gameType) {
        return 0;
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

    @Override
    public void playMoveAt(String move) {
        board.updateAt(Integer.valueOf(move), currentPlayer);
        if (board.hasWinningCombination()) {
            printWinningMessageFor(currentPlayer);
        }

        if (!board.hasFreeSpace()) {
            printDrawMessage();
        }
        currentPlayer = PlayerSymbol.opponent(currentPlayer);
    }

    @Override
    public PlayerSymbol getCurrentPlayer() {
        return currentPlayer;
    }
}
