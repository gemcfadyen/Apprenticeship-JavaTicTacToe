package ttt.commandlineapp.players;

import ttt.game.board.Board;
import ttt.game.Player;
import ttt.game.PlayerSymbol;
import ttt.game.ReadPrompt;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayerSymbol symbol, ReadPrompt prompt) {
        super(symbol, prompt);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        return prompt.readNextMove(board);
    }
}
