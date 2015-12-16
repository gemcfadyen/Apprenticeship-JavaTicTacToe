package ttt.player;

import ttt.board.Board;

public class GuiHumanPlayer extends Player {

    public static final int IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY = -1;
    private int nextMove = -1;

    public GuiHumanPlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int move = nextMove;
        nextMove = -1;
        return move;
    }

    public boolean isReady() {
        return nextMove != -1;
    }

    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }
}
