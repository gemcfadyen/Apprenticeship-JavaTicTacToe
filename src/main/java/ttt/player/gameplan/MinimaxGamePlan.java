package ttt.player.gameplan;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public class MinimaxGamePlan {

    public int execute(Board board, PlayerSymbol maxPlayerSymbol, int depth, boolean isMaxPlayer) {
        if (board.hasWinningCombination()) {
            return scoreForWinningBoard(board, maxPlayerSymbol, depth);
        }

        //get all possible moves .. do minimax recursively storing move and score

        return 0;
    }

    private int scoreForWinningBoard(Board board, PlayerSymbol maxPlayer, int depth) {
        if (board.getWinningSymbol() == maxPlayer) {
            return 10 - depth;
        } else {
            return depth - 10;
        }
    }
}
