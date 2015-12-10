package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

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
        this.gameType = gameType;
        String dimension = gameConfiguration.getDimension(gameType);
        boardView.presentGridDimensionsUpTo(dimension);
    }

    @Override
    public void presentBoard(String dimension) {
        ticTacToeRules.initialiseGame(gameType, dimension);
        Board board = ticTacToeRules.getBoard();
        boardView.presentsBoard(board);
    }

    @Override
    public void playMove(String position) {
        ticTacToeRules.playMoveAt(position);
        Board board = ticTacToeRules.getBoard();
        boardView.presentsBoard(board);

        displayExitMessage(board);
        togglePlayer();
    }

    GameType getGameType() {
        return gameType;
    }

    private void togglePlayer() {
        ticTacToeRules.togglePlayer();
    }

    private void displayExitMessage(Board board) {
        if (ticTacToeRules.hasWinner()) {
            boardView.printsWinningMessage(board, ticTacToeRules.getWinningSymbol());
        } else if (!ticTacToeRules.boardHasFreeSpace()) {
            boardView.printsDrawMessage(board);
        }
    }
}
