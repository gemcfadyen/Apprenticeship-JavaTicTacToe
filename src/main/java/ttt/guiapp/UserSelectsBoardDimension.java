package ttt.guiapp;

import ttt.game.GameController;

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
