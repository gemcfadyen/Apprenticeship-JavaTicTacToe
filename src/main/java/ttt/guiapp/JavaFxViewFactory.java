package ttt.guiapp;

import javafx.scene.Scene;
import ttt.game.DisplayPresenter;
import ttt.game.GameRules;

public class JavaFxViewFactory implements ViewFactory {
    private Scene scene;

    public JavaFxViewFactory(Scene scene) {
        this.scene = scene;
    }

    public DisplayPresenter createView(GuiGameController controller, GameRules gameRules) {
        return new TicTacToeBoardPresenter(controller, scene);
    }
}
