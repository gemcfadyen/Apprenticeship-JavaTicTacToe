package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

public class GuiGameController implements GameController {

    private GameRules ticTacToeRules;
    private BoardPresenter boardView;

    public GuiGameController(GameRules ticTacToeRules, ViewFactory viewFactory) {
        this.ticTacToeRules = ticTacToeRules;
        this.boardView = viewFactory.createView(this, ticTacToeRules);
    }

    @Override
    public void presentGameTypes() {
        GameType gameTypes = ticTacToeRules.getGameTypes();
        boardView.presentGameTypes(gameTypes.gameNameForDisplay());
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        String dimension = ticTacToeRules.getDimension(gameType);
        boardView.presentGridDimensionsFor(dimension);
    }

    @Override
    public void presentBoard(String dimension) {
        ticTacToeRules.initialiseGame(dimension);
        Board board = ticTacToeRules.getBoard();
        boardView.presentsBoard(board);
    }
}
