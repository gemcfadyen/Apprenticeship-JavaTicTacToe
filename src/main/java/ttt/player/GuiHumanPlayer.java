package ttt.player;

import ttt.board.Board;

public class GuiHumanPlayer extends Player implements ConfigurableMovePlayer {
    public static final int IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY = -1;
    private int moveFromGui = IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;

    public GuiHumanPlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int preloadedMove = moveFromGui;
        moveFromGui = IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;
        return preloadedMove;
    }

    @Override
    public boolean isReady() {
        return moveFromGui != IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;
    }

    @Override
    public void setMove(int moveFromGui) {
        this.moveFromGui = moveFromGui;
    }
}
