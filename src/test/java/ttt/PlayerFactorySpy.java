package ttt;

import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.ui.Prompt;

public class PlayerFactorySpy extends PlayerFactory {
    private Player[] players;

    public PlayerFactorySpy(Player... players) {
        this.players = players;
    }

    @Override
    public Player[] createPlayers(GameType playerOption, Prompt prompt) {
        return players;
    }
}
