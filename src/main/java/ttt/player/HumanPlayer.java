package ttt.player;

import ttt.board.Board;
import ttt.ui.Prompt;

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
