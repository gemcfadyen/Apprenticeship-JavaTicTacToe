package ttt.guiapp.eventlisteners;

import ttt.game.GameController;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.ClickableElement;

public class UserSelectsBoardDimension implements ClickEvent {
    private GameController controller;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(GameController controller, ClickableElement dimensionSelectionButton) {
        this.controller = controller;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        controller.startGame(Integer.valueOf(dimensionSelectionButton.getText()));
    }
}
