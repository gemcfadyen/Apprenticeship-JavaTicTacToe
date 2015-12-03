package ttt.gui;

import ttt.ui.WritePromptForGui;

public class UserSelectsBoardDimension implements ClickEvent {
    private final GameInterface gameRules;
    private WritePromptForGui guiPrompt;
    private ClickableElement dimensionSelectionButton;

    public UserSelectsBoardDimension(GameInterface gameRules, WritePromptForGui guiPrompt, ClickableElement dimensionSelectionButton) {
        this.gameRules = gameRules;
        this.guiPrompt = guiPrompt;
        this.dimensionSelectionButton = dimensionSelectionButton;
    }

    @Override
    public void action() {
        String dimension = dimensionSelectionButton.getText();
        String dimensionForBoard = String.valueOf(dimension.charAt(0));

        gameRules.createBoard(Integer.valueOf(dimensionForBoard));
        guiPrompt.printBoard();
    }
}
