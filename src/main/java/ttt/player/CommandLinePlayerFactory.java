package ttt.player;

import ttt.ui.Prompt;

public class CommandLinePlayerFactory extends PlayerFactory {

    public CommandLinePlayerFactory(Prompt playerPrompt) {
        super(playerPrompt);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}
