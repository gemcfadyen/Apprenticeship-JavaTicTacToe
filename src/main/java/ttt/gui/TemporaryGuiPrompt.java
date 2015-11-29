package ttt.gui;

import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public interface TemporaryGuiPrompt extends Prompt {
    void playMoveAt(String move);
    PlayerSymbol getCurrentPlayer();
}
