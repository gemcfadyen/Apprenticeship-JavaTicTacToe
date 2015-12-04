package ttt.gui;

import ttt.GameType;

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

    }
}
