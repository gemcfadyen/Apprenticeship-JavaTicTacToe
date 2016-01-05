package ttt.game;

import ttt.game.board.Board;

public class PlayerSpy extends Player {
    private int numberOfTurnsTaken;

    public PlayerSpy(PlayerSymbol symbol, ReadPrompt prompt) {
        super(symbol, prompt);
        this.numberOfTurnsTaken = 0;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        numberOfTurnsTaken++;
        return prompt.readNextMove(board);
    }

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
