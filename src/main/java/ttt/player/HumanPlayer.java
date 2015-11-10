package ttt.player;

import ttt.board.Board;
import ttt.ui.Prompt;

public class HumanPlayer extends Player {

    public HumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return prompt.getNextMove(board);
    }
}
