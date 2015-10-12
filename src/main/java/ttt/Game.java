package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 09/10/15.
 */
public class Game {
    private final HumanPlayer[] players;
    private Board board;

    public Game(Board board, HumanPlayer... players) {
        this.board = board;
        this.players = players;
    }

    public String play() throws IOException {

        int playerIndex = 0;
        while (board.hasFreeSpace()) {

            int nextMove = players[playerIndex].chooseNextMoveFrom(board);

            board.updateAt(nextMove, players[playerIndex].getSymbols());
            playerIndex = toggle(playerIndex);
            
            if (board.hasWinningCombination()) {
                return "Win";
            }
        }
        return "Draw";
    }

    private int toggle(int playerIndex) {
        return playerIndex == 0 ? 1 : 0;
    }
}
