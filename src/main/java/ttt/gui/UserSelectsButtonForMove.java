package ttt.gui;

public class UserSelectsButtonForMove implements ClickEvent {
    private GameController controller;
    private DeactivatableElement deactivatableElement;

    public UserSelectsButtonForMove(GameController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        controller.playMove(deactivatableElement.getId());
    }
}
