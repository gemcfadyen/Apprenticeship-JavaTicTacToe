package ttt;

import ttt.gui.UnusedPrompt;
import ttt.player.Player;
import ttt.player.CommandLinePlayerFactory;

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
