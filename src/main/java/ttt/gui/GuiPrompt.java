package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class GuiPrompt implements GameRulesPrompt {
    private BoardPresenter boardPresenter;
    private GameRules gameRules;
    private Board board;
    private PlayerSymbol currentPlayer;

    public GuiPrompt(BoardPresenter boardPresenter, GameRules gameRules) {
        this.boardPresenter = boardPresenter;
        this.gameRules = gameRules;
        this.currentPlayer = X;
    }

    GuiPrompt(BoardPresenter boardPresenter, Board board, GameRules gameRules) {
        this(boardPresenter, gameRules);
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
        GameType gameType = gameRules.getGameTypes();
        boardPresenter.presentGameTypes(gameType.gameNameForDisplay());
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        boardPresenter.presentGridDimensionsFor(gameType);
    }

    public void playMoveAt(String move) {
        gameRules.playMoveAt(move);
        if (gameRules.hasWinner()) {
            printWinningMessageFor(currentPlayer);
        } else if (!gameRules.hasFreeSpace()) {
            printDrawMessage();
        }
        gameRules.togglePlayer();
    }

    public PlayerSymbol getCurrentPlayer() {
        return gameRules.getCurrentPlayer();
    }
}
