package ttt.gui;

import ttt.GameType;
import ttt.ui.WritePrompt;

public class UserSelectsGameType implements ClickEvent {
    private WritePrompt guiPrompt;
    private ClickableElement selectedRadioBox;

    public UserSelectsGameType(WritePrompt guiPrompt, ClickableElement selectedRadioBox) {
        this.guiPrompt = guiPrompt;
        this.selectedRadioBox = selectedRadioBox;
    }

    @Override
    public void action() {
        guiPrompt.presentBoardDimensionsFor(
                GameType.of(selectedRadioBox.getText()));
    }
}
