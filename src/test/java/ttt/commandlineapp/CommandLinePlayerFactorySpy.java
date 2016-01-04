package ttt.commandlineapp;

import ttt.commandlineapp.players.CommandLinePlayerFactory;
import ttt.game.GameType;
import ttt.game.Player;

public class CommandLinePlayerFactorySpy extends CommandLinePlayerFactory {
    private Player[] players;
    private GameType gameType;

    public CommandLinePlayerFactorySpy(Player... players) {
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
