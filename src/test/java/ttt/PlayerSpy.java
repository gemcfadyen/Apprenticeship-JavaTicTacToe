package ttt;

import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class PlayerSpy extends Player {
    private int numberOfTurnsTaken;

    public PlayerSpy(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
        this.numberOfTurnsTaken = 0;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        numberOfTurnsTaken++;
        return Integer.valueOf(prompt.getNextMove(board));
    }

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
