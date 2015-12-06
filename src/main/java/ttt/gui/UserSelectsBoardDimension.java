package ttt.gui;

public class UserSelectsBoardDimension implements ClickEvent {
    private GameController controller;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(GameController controller, ClickableElement dimensionSelectionButton) {
        this.controller = controller;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        controller.presentBoard(dimensionSelectionButton.getText());
    }
}
