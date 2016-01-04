package ttt.game;

import java.util.List;

public class DelayedUnbeatablePlayer extends Player {
    private static final int NUMBER_OF_VACANT_SLOTS_FOR_MINIMAX_TO_START = 12;
    private final UnbeatablePlayer unbeatablePlayer;

    public DelayedUnbeatablePlayer(PlayerSymbol symbol, UnbeatablePlayer unbeatablePlayer) {
        super(symbol);
        this.unbeatablePlayer = unbeatablePlayer;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        List<Integer> vacantPositions = board.getVacantPositions();

        if (vacantPositions.size() < NUMBER_OF_VACANT_SLOTS_FOR_MINIMAX_TO_START) {
            return unbeatablePlayer.chooseNextMoveFrom(board);
        }
        return vacantPositions.get(0);
    }
}
