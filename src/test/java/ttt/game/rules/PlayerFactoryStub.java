package ttt.game.rules;

import ttt.game.*;
import ttt.game.players.FakePlayer;

public class PlayerFactoryStub extends PlayerFactory {

    private final Player[] players;

    public PlayerFactoryStub(Player... players) {
        super(new UnusedPrompt());
        this.players = players;
    }

    @Override
    public Player[] createPlayers(GameType playerOption, int dimension) {
        return players;
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, ReadPrompt prompt) {
        return new FakePlayer(symbol);
    }
}
