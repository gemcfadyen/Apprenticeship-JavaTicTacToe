package ttt;

import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class PlayerSpy extends Player {
    private int numberOfTurnsTaken;

    public PlayerSpy(PlayerSymbol symbol, Prompt prompt) {
        super(symbol, prompt);
        this.numberOfTurnsTaken = 0;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        numberOfTurnsTaken++;
        return prompt.readNextMove(board);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
