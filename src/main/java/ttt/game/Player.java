package ttt.game;

public abstract class Player {
    private PlayerSymbol symbol;
    protected ReadPrompt prompt;

    public Player(PlayerSymbol symbol) {
        this.symbol = symbol;
    }

    public Player(PlayerSymbol symbol, ReadPrompt prompt) {
        this(symbol);
        this.prompt = prompt;
    }

    public abstract int chooseNextMoveFrom(Board board);

    public PlayerSymbol getSymbol() {
        return symbol;
    }

    public boolean isReady() {
        return true;
    }
}
