package ttt.gui;

import javafx.scene.Scene;

public class JavaFxViewFactory implements ViewFactory {
    private Scene scene;
    private GameRules gameRules; //TODO remove after tttboard presenter is in shape

    public JavaFxViewFactory(Scene scene, GameRules gameRules) {
        this.scene = scene;
        this.gameRules = gameRules;
    }

    public BoardPresenter createView(GuiGameController controller, GameRules gameRules) {
        return new TicTacToeBoardPresenter(gameRules, controller, scene);
    }
}
