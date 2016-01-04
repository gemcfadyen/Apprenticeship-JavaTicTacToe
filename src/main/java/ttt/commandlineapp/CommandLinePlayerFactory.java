package ttt.commandlineapp;

import ttt.game.Player;
import ttt.game.PlayerFactory;
import ttt.game.PlayerSymbol;

public class CommandLinePlayerFactory extends PlayerFactory {

    public CommandLinePlayerFactory(Prompt playerPrompt) {
        super(playerPrompt);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}
