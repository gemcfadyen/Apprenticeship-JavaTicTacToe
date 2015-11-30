package ttt.gui;

import ttt.board.Board;

public class UserSelectsBoardDimension implements ClickEvent {
    private GameRulesPrompt guiPrompt;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(GameRulesPrompt guiPrompt, ClickableElement dimensionSelectionButton) {
        this.guiPrompt = guiPrompt;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        String dimension = dimensionSelectionButton.getText();
        String dimensionForBoard = String.valueOf(dimension.charAt(0));

        Board board = new Board(Integer.valueOf(dimensionForBoard));

        guiPrompt.print(board);
    }
}
