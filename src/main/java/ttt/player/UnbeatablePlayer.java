package ttt.player;

import ttt.board.Board;
import ttt.ui.Prompt;

public class UnbeatablePlayer extends Player {
    public UnbeatablePlayer(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return 0;
    }
}
