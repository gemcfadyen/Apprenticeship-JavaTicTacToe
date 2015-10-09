package ttt;

/**
 * Created by Georgina on 09/10/15.
 */
public class Game {
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public String play() {
        if (board.containsWinningRow()) {
            return "Win";
        } else {
            return "Draw";
        }
    }
}
