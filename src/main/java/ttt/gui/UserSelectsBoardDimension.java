package ttt.gui;

import ttt.ui.Prompt;

public class UserSelectsBoardDimension implements ClickEvent {
    private Prompt guiPrompt;

    public UserSelectsBoardDimension(Prompt guiPrompt) {
        this.guiPrompt = guiPrompt;
    }

    @Override
    public void action() {

        //get the board from somewhere or create a game with the board criteria?
        //guiPrompt.print(board);
    }
}
