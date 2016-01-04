package ttt.guiapp;

import ttt.commandlineapp.Prompt;
import ttt.game.Player;
import ttt.game.PlayerFactory;
import ttt.game.PlayerSymbol;

public class GuiPlayerFactory extends PlayerFactory {

    public GuiPlayerFactory() {
        super(null);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, Prompt unused) {
        return new GuiHumanPlayer(symbol);
    }
}
