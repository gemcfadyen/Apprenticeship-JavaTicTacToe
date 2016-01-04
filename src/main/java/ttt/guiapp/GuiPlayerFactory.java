package ttt.guiapp;

import ttt.game.Player;
import ttt.game.PlayerFactory;
import ttt.game.PlayerSymbol;
import ttt.game.ReadPrompt;

public class GuiPlayerFactory extends PlayerFactory {

    public GuiPlayerFactory() {
        super(null);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, ReadPrompt unused) {
        return new GuiHumanPlayer(symbol);
    }
}
