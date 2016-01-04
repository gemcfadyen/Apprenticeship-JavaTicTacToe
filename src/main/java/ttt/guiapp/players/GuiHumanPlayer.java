package ttt.guiapp.players;

import ttt.game.board.Board;
import ttt.game.Player;
import ttt.game.PlayerSymbol;

public class GuiHumanPlayer extends Player implements MoveObserver {
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
    public void update(int moveFromGui) {
        this.moveFromGui = moveFromGui;
    }
}
