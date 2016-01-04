package ttt.guiapp;

import ttt.game.GameController;

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
