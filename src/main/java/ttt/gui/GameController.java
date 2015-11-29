package ttt.gui;

import ttt.Game;
import ttt.ui.Prompt;

public class GameController {
    private final Game game;
    private Prompt guiPrompt;

    public GameController(Game game, Prompt guiPrompt) {
        this.game = game;
        this.guiPrompt = guiPrompt;
    }

    public void presentGameTypes() {
       guiPrompt.presentGameTypes();
    }
}
