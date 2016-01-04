package ttt.commandlineapp;

import ttt.commandlineapp.players.CommandLinePlayerFactory;
import ttt.game.GameType;
import ttt.game.Player;

public class CommandLinePlayerFactoryStub extends CommandLinePlayerFactory {
    private Player[] players;

    public CommandLinePlayerFactoryStub(Player... players) {
        super(new UnusedPrompt());
        this.players = players;
    }

    @Override
    public Player[] createPlayers(GameType playerOption, int dimension) {
        return players;
    }
}
