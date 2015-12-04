package ttt.gui;

import ttt.GameType;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.ui.Prompt;

public class PlayerFactorySpy extends PlayerFactory {
    private Player[] players;
    private GameType gameType;

    PlayerFactorySpy(Player... players) {
        this.players = players;
    }

    public Player[] createPlayers(GameType gameType, Prompt prompt, int dimension) {
        this.gameType = gameType;
        return players;
    }

    public GameType getGameTypeUsed() {
        return gameType;
    }
}
