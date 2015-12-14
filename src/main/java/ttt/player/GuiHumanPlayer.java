package ttt.player;

import ttt.board.Board;

public class GuiHumanPlayer extends Player {

    public static final int PLACEHOLDER_UNUSED_MOVE = -1;

    public GuiHumanPlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return PLACEHOLDER_UNUSED_MOVE;
    }
}
