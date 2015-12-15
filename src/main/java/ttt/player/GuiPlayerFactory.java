package ttt.player;

import ttt.ui.Prompt;

public class GuiPlayerFactory extends PlayerFactory {

    public GuiPlayerFactory(Prompt unused) {
        super(unused);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, Prompt unused) {
        return new GuiHumanPlayer(symbol);
    }
}
