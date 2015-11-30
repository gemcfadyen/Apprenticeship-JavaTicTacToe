package ttt.gui;

import ttt.player.PlayerSymbol;
import ttt.ui.WritePrompt;

public interface GameRulesPrompt extends WritePrompt {
    void playMoveAt(String move);
    PlayerSymbol getCurrentPlayer();
}
