package ttt.guiapp.eventlisteners;

import ttt.game.GameController;
import ttt.guiapp.ClickEvent;

public class UserSelectsReplay implements ClickEvent {
    private final GameController controller;

    public UserSelectsReplay(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void action() {
        controller.presentGameTypes();
    }
}
