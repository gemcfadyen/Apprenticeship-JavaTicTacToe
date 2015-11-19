package ttt.player;

import ttt.board.Board;

import java.util.List;

public class DelayedUnbeatablePlayer extends Player {
    private final UnbeatablePlayer unbeatablePlayer;

    public DelayedUnbeatablePlayer(PlayerSymbol symbol, UnbeatablePlayer unbeatablePlayer) {
        super(symbol);
        this.unbeatablePlayer = unbeatablePlayer;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        List<Integer> vacantPositions = board.getVacantPositions();

        if (vacantPositions.size() < 12) {
            return unbeatablePlayer.chooseNextMoveFrom(board);
        }
        return vacantPositions.get(0);
    }
}
