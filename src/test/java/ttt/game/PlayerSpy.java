package ttt.game;

import ttt.game.Board;
import ttt.game.Player;
import ttt.game.PlayerSymbol;
import ttt.commandlineapp.Prompt;

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

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
