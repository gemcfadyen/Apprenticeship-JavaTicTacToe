package ttt.gui;


public class UserSelectsBoardDimension implements ClickEvent {
    private TTTController controller;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(TTTController controller, ClickableElement dimensionSelectionButton) {
        this.controller = controller;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        String dimension = dimensionSelectionButton.getText();
        String dimensionForBoard = String.valueOf(dimension.charAt(0));

        controller.presentBoard(dimensionForBoard);
    }
}
