package ttt.player;

import ttt.board.Board;

public class GuiHumanPlayer extends Player {

    public static final int IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY = -1;

    public GuiHumanPlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;
    }
}
