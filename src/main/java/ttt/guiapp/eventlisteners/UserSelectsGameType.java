package ttt.guiapp.eventlisteners;

import ttt.game.GameController;
import ttt.game.GameType;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.ClickableElement;

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
