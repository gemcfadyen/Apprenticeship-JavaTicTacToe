package ttt.gui;

import javafx.scene.Scene;

public class JavaFxViewFactory implements ViewFactory {
    private Scene scene;

    public JavaFxViewFactory(Scene scene) {
        this.scene = scene;
    }

    public BoardPresenter createView(GuiGameController controller, GameRules gameRules) {
        return new TicTacToeBoardPresenter(controller, scene);
    }
}
