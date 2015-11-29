package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GuiPrompt implements Prompt {
    private BoardPresenter boardPresenter;

    public GuiPrompt(BoardPresenter boardPresenter) {
        this.boardPresenter = boardPresenter;
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
        boardPresenter.presentsBoard(board);
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void printDrawMessage() {

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
