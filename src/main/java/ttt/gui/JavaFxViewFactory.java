package ttt.gui;

import javafx.scene.Scene;

public class JavaFxViewFactory implements ViewFactory {
    private Scene scene;

    public JavaFxViewFactory(Scene scene) {
        this.scene = scene;
    }

    public DisplayPresenter createView(GuiGameController controller, GameTypeController gameTypeController, GameRules gameRules) {
        final RegisterClickEvent registerClickEvent = new RegisterClickEvent();
        return new TicTacToeBoardPresenter(controller, gameTypeController, scene, registerClickEvent, new RegisterRollEvent(),
                new GameTypesPresenter(scene, registerClickEvent, controller));
    }
}
