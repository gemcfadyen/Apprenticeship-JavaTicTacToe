package ttt;

public abstract class Player {
    private PlayerSymbol symbol;
    protected Prompt prompt;

    public Player(Prompt prompt, PlayerSymbol symbol) {
        this.prompt = prompt;
        this.symbol = symbol;
    }

    abstract int chooseNextMoveFrom(Board board);

    public PlayerSymbol getSymbol() {
        return symbol;
    }
}
