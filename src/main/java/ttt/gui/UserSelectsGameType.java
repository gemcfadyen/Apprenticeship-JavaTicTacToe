package ttt.gui;

import ttt.GameType;

public class UserSelectsGameType implements ClickEvent {
    private GameController controller;
    private ClickableElement selectedRadioBox;

    public UserSelectsGameType(GameController controller, ClickableElement selectedRadioBox) {
        this.controller = controller;
        this.selectedRadioBox = selectedRadioBox;
    }

    @Override
    public void action() {
        controller.presentBoardDimensionsFor(
                GameType.of(selectedRadioBox.getText())
        );
    }
}
