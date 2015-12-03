package ttt.gui;


public class UserSelectsBoardDimension implements ClickEvent {
    private final GameInterface gameRules;
    private TTTController controller;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(GameInterface gameRules, TTTController controller, ClickableElement dimensionSelectionButton) {
        this.gameRules = gameRules;
        this.controller = controller;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        String dimension = dimensionSelectionButton.getText();
        String dimensionForBoard = String.valueOf(dimension.charAt(0));

        gameRules.createBoard(Integer.valueOf(dimensionForBoard));
        controller.printBoard();
    }
}
