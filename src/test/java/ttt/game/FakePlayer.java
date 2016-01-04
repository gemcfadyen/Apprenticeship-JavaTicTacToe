package ttt.game;

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
}
