package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class GuiPrompt implements GameRulesPrompt {
    private BoardPresenter boardPresenter;
    private TicTacToeRules ticTacToeRules;
    private Board board;
    private PlayerSymbol currentPlayer;

    public GuiPrompt(BoardPresenter boardPresenter, TicTacToeRules ticTacToeRules) {
        this.boardPresenter = boardPresenter;
        this.ticTacToeRules = ticTacToeRules;
        this.currentPlayer = X;
    }

    GuiPrompt(BoardPresenter boardPresenter, Board board, TicTacToeRules ticTacToeRules) {
        this(boardPresenter, ticTacToeRules);
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
        GameType gameType = ticTacToeRules.getGameTypes();
        boardPresenter.presentGameTypes(gameType.gameNameForDisplay());
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        boardPresenter.presentGridDimensionsFor("3");
    }

    public void playMoveAt(String move) {
        ticTacToeRules.playMoveAt(move);
        if (ticTacToeRules.hasWinner()) {
            printWinningMessageFor(currentPlayer);
        } else if (!ticTacToeRules.hasFreeSpace()) {
            printDrawMessage();
        }
        ticTacToeRules.togglePlayer();
    }

    public PlayerSymbol getCurrentPlayer() {
        return ticTacToeRules.getCurrentPlayer();
    }
}
