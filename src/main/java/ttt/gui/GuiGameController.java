package ttt.gui;

import ttt.GameType;

public class GuiGameController {

    private GameRules ticTacToeRules;
    private BoardPresenter boardView;

    public GuiGameController(GameRules ticTacToeRules, ViewFactory viewFactory) {
        this.ticTacToeRules = ticTacToeRules;
        this.boardView = viewFactory.createView(this, ticTacToeRules);
    }

    public void displayGameTypes() {
        GameType gameTypes = ticTacToeRules.getGameTypes();
        boardView.presentGameTypes(gameTypes.gameNameForDisplay());
    }
}
