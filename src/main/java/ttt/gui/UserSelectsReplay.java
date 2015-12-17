package ttt.gui;

public class UserSelectsReplay implements ClickEvent {
    private final GameController controller;
    private GameTypeController gameTypeController;

    public UserSelectsReplay(GameController controller, GameTypeController gameTypeController) {
        this.controller = controller;
        this.gameTypeController = gameTypeController;
    }

    @Override
    public void action() {
        controller.presentGameTypes();
    }
}
