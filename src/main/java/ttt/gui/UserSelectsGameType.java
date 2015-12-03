package ttt.gui;

import ttt.GameType;
import ttt.ui.WritePromptForGui;

public class UserSelectsGameType implements ClickEvent {
    private WritePromptForGui guiPrompt;
    private ClickableElement selectedRadioBox;

    public UserSelectsGameType(WritePromptForGui guiPrompt, ClickableElement selectedRadioBox) {
        this.guiPrompt = guiPrompt;
        this.selectedRadioBox = selectedRadioBox;
    }

    @Override
    public void action() {
        guiPrompt.presentBoardDimensionsFor(
                GameType.of(selectedRadioBox.getText()));
    }
}
