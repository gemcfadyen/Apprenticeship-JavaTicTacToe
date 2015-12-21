package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.GuiHumanPlayer;
import ttt.player.Player;

import java.util.List;

public class GuiGameController implements GameController {

    private final GameConfiguration gameConfiguration;
    private GameRules ticTacToeRules;
    private DisplayPresenter boardView;
    private GameType gameType;

    public GuiGameController(GameConfiguration gameConfiguration, GameRules ticTacToeRules, ViewFactory viewFactory) {
        this.gameConfiguration = gameConfiguration;
        this.ticTacToeRules = ticTacToeRules;
        this.boardView = viewFactory.createView(this, ticTacToeRules);
    }

    @Override
    public void presentGameTypes() {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        boardView.presentGameTypes(allGameTypes);
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        setGameType(gameType);
        boardView.presentGridDimensionsBetween(gameType.dimensionLowerBoundary(), gameType.dimensionUpperBoundary());
    }

    @Override
    public void presentBoard(int dimension) {
        ticTacToeRules.initialiseGame(gameType, dimension);
        Board board = ticTacToeRules.getBoard();
        ticTacToeRules.playGame();
        printBoard(board);
    }

    @Override
    public void playMove(int position) {
        preloadHumanWithMoveAt(position);

        ticTacToeRules.playGame();
        Board latestBoard = ticTacToeRules.getBoard();
        printBoard(latestBoard);
        displayExitMessage(latestBoard);
    }

    private void printBoard(Board board) {
        boardView.presentsBoard(board);
    }

    private void preloadHumanWithMoveAt(int position) {
        Player currentPlayer = getCurrentPlayer();
        ((GuiHumanPlayer)currentPlayer).update(Integer.valueOf(position));
    }

    private Player getCurrentPlayer() {
        return ticTacToeRules.getCurrentPlayer();
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    GameType getGameType() {
        return gameType;
    }

    private void displayExitMessage(Board board) {
        if (ticTacToeRules.hasWinner()) {
            boardView.printsWinningMessage(board, ticTacToeRules.getWinningSymbol());
        } else if (!ticTacToeRules.hasAvailableMoves()) {
            boardView.printsDrawMessage(board);
        }
    }
}
