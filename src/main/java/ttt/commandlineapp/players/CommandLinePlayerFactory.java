package ttt.commandlineapp.players;

import ttt.commandlineapp.Prompt;
import ttt.commandlineapp.players.HumanPlayer;
import ttt.game.Player;
import ttt.game.PlayerFactory;
import ttt.game.PlayerSymbol;
import ttt.game.ReadPrompt;

public class CommandLinePlayerFactory extends PlayerFactory {

    public CommandLinePlayerFactory(Prompt playerPrompt) {
        super(playerPrompt);
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, ReadPrompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}
