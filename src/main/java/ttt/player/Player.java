package ttt.player;

import ttt.board.Board;
import ttt.ui.Prompt;

public abstract class Player {
    private PlayerSymbol symbol;
    protected Prompt prompt;

    public Player(PlayerSymbol symbol) {
        this.symbol = symbol;
    }

    public Player(PlayerSymbol symbol, Prompt prompt) {
        this(symbol);
        this.prompt = prompt;
    }

    public abstract int chooseNextMoveFrom(Board board);

    public PlayerSymbol getSymbol() {
        return symbol;
    }
}
