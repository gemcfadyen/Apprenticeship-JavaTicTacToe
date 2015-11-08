package ttt;

public class PlayerFactorySpy extends PlayerFactory {
    private Player[] players;

    public PlayerFactorySpy(Player... players) {
        this.players = players;
    }

    @Override
    public Player[] createPlayers(int playerOption, Prompt prompt) {
        return players;
    }
}
