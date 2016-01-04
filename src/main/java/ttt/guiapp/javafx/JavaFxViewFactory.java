package ttt.guiapp.javafx;

import javafx.scene.Scene;
import ttt.game.DisplayPresenter;
import ttt.game.GameRules;
import ttt.guiapp.GuiGameController;
import ttt.guiapp.ViewFactory;

public class JavaFxViewFactory implements ViewFactory {
    private Scene scene;

    public JavaFxViewFactory(Scene scene) {
        this.scene = scene;
    }

    public DisplayPresenter createView(GuiGameController controller, GameRules gameRules) {
        return new TicTacToeBoardPresenter(controller, scene);
    }
}
