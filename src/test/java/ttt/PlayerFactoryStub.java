package ttt;

import ttt.gui.UnusedPrompt;
import ttt.player.Player;
import ttt.player.PlayerFactory;

public class PlayerFactoryStub extends PlayerFactory {
    private Player[] players;

    public PlayerFactoryStub(Player... players) {
        super(new UnusedPrompt());
        this.players = players;
    }

    @Override
    public Player[] createPlayers(GameType playerOption, int dimension) {
        return players;
    }
}
