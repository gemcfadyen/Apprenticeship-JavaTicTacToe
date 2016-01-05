package ttt.game;

import ttt.game.players.FakePlayer;

public class PlayerFactorySpy extends PlayerFactory {
    private Player[] players;
    private GameType gameType;

    public PlayerFactorySpy(Player... players) {
        super(new UnusedPrompt());
        this.players = players;
    }

    public Player[] createPlayers(GameType gameType, int dimension) {
        this.gameType = gameType;
        return players;
    }

    @Override
    public Player createHumanPlayer(PlayerSymbol symbol, ReadPrompt prompt) {
        return new FakePlayer(symbol);
    }

    public GameType getGameTypeUsed() {
        return gameType;
    }
}
