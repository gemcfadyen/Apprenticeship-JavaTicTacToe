package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class GameTest {

    @Test
    public void reportsDrawWhenBoardIsFull() {
        Board board = new Board("X", "X", "O", "O", "X", "X", "X", "O", "O");
        HumanPlayer player = new HumanPlayer("O");
        Game game = new Game(board, player);

        String status = game.play();

        assertThat(status, is("Draw"));
    }


    @Test
    public void reportsWinWhenPlayerMakesWinningMove() {
        Board board = new Board("X", "X", "-", "O", "X", "X", "X", "O", "O");
        HumanPlayer player = new HumanPlayer("X");
        Game game = new Game(board, player);

        String status = game.play();

        assertThat(status, is("Win"));
    }
}