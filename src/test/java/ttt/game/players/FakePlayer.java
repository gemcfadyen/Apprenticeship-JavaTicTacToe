package ttt.game.players;

import ttt.game.Player;
import ttt.game.PlayerSymbol;
import ttt.game.board.Board;

public class FakePlayer extends Player {
    private final int[] moves;
    private int currentMoveIndex = 0;

    public FakePlayer(PlayerSymbol symbol, int... moves) {
        super(symbol, null);
        this.moves = moves;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return moves[currentMoveIndex++];
    }

    public boolean isReady() {
        return moves.length != 0;
    }
}
