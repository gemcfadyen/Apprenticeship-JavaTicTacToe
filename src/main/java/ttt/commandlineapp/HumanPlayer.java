package ttt.commandlineapp;

import ttt.game.Board;
import ttt.game.Player;
import ttt.game.PlayerSymbol;

public class HumanPlayer extends Player {
    private Prompt prompt;

    public HumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        super(symbol);
        this.prompt = prompt;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return prompt.readNextMove(board);
    }
}
