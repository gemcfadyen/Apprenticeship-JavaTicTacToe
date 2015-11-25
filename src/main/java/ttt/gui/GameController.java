package ttt.gui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GameController implements Prompt, WritePrompt {
    private BoardPresenter boardPresenter;

    public GameController(BoardPresenter boardPresenter) {
        this.boardPresenter = boardPresenter;
    }

    @Override
    public int getBoardDimension(GameType gameType) {
        return 0;
    }

    @Override
    public GameType getGameType() {
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

    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void printDrawMessage() {

    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        //displays the grid dimensions to the useri
        //same as askTheUserForGridDimensions in the current prompt (private method at the moment)
        //writePrompt.askForDimensions(gameType);
        boardPresenter.presentGridDimensionsFor(gameType);
    }
}
