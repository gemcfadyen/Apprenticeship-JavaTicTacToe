package ttt.guiapp;

import ttt.game.*;
import ttt.game.board.Board;

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
    public void startGame(int dimension) {
        ticTacToeRules.initialiseGame(gameType, dimension);
        playTurns();
    }

    @Override
    public void takeMove(int position) {
        playTurns();
        displayExitMessage(ticTacToeRules.getBoard());
    }

    @Override
    public Player getCurrentPlayer() {
        return ticTacToeRules.getCurrentPlayer();
    }

    void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    GameType getGameType() {
        return gameType;
    }

    private void playTurns() {
        ticTacToeRules.playGame();
        Board board = ticTacToeRules.getBoard();
        boardView.presentsBoard(board);
    }

    private void displayExitMessage(Board board) {
        if (ticTacToeRules.hasWinner()) {
            boardView.printsWinningMessage(board, ticTacToeRules.getWinningSymbol());
        } else if (!ticTacToeRules.hasAvailableMoves()) {
            boardView.printsDrawMessage(board);
        }
    }
}
