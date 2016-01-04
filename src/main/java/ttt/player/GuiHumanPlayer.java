package ttt.player;

import ttt.board.Board;

public class GuiHumanPlayer extends Player implements ConfigurableMovePlayer {
    private static final int UNSET_MOVE = -1;
    private int moveFromGui = UNSET_MOVE;

    public GuiHumanPlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int preloadedMove = moveFromGui;
        moveFromGui = UNSET_MOVE;
        return preloadedMove;
    }

    @Override
    public boolean isReady() {
        return moveFromGui != UNSET_MOVE;
    }

    @Override
    public void setMove(int moveFromGui) {
        this.moveFromGui = moveFromGui;
    }
}
