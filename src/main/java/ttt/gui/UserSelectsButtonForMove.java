package ttt.gui;

public class UserSelectsButtonForMove implements ClickEvent {
    private TicTacToeBoardController controller;
    private DeactivatableElement deactivatableElement;

    public UserSelectsButtonForMove(TicTacToeBoardController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        controller.playMove(deactivatableElement.getId());
    }
}
