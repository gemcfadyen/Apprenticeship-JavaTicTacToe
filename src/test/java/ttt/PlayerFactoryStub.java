package ttt;

import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.ui.Prompt;

public class PlayerFactoryStub extends PlayerFactory {
    private Player[] players;

    public PlayerFactoryStub(Player... players) {
        this.players = players;
    }

    @Override
    public Player[] createPlayers(GameType playerOption, Prompt prompt, int dimension) {
        return players;
    }
}
