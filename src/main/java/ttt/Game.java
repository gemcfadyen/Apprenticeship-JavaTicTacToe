package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Game {
    private final HumanPlayer firstPlayer;
    private Board board;

    public Game(Board board, HumanPlayer firstPlayer) {
        this.board = board;
        this.firstPlayer = firstPlayer;
    }

    public String play() {
        int nextMove = firstPlayer.chooseNextMoveFrom(board);
        board.updateAt(nextMove, firstPlayer.getSymbols());
        if (board.hasWinningCombination()) {
            return "Win";
        } else {
            return "Draw";
        }
    }
}
