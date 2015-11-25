package ttt.gui;

import ttt.ui.WritePrompt;

import static ttt.GameType.HUMAN_VS_HUMAN;

public class UserSelectsGameType implements ClickEvent {
    private ClickableElement gameSelectionRadioBox;
    private WritePrompt gameController;

    public UserSelectsGameType(ClickableElement gameSelectionRadioBox, WritePrompt gameController) {
        this.gameSelectionRadioBox = gameSelectionRadioBox;
        this.gameController = gameController;
    }

    @Override
    public void action() {
        gameController.presentBoardDimensionsFor(HUMAN_VS_HUMAN);
        gameSelectionRadioBox.disable();
    }
}
