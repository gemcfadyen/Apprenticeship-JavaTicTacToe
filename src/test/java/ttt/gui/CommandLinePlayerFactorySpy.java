package ttt.gui;

import ttt.GameType;
import ttt.player.Player;
import ttt.player.CommandLinePlayerFactory;

public class CommandLinePlayerFactorySpy extends CommandLinePlayerFactory {
    private Player[] players;
    private GameType gameType;

    CommandLinePlayerFactorySpy(Player... players) {
        super(new UnusedPrompt());
        this.players = players;
    }

    public Player[] createPlayers(GameType gameType, int dimension) {
        this.gameType = gameType;
        return players;
    }

    public GameType getGameTypeUsed() {
        return gameType;
    }
}
