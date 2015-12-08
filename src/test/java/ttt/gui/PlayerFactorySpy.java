package ttt.gui;

import ttt.GameType;
import ttt.player.Player;
import ttt.player.PlayerFactory;

public class PlayerFactorySpy extends PlayerFactory {
    private Player[] players;
    private GameType gameType;

    PlayerFactorySpy(Player... players) {
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
