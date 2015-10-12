package ttt;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class GameTest {

    @Test
    public void reportsDrawWhenBoardIsFull() throws IOException {
        Board board = new Board("X", "X", "-", "O", "X", "X", "X", "O", "O");
        Prompt prompt = new FakePrompt("2");
        HumanPlayer player = new HumanPlayer(prompt, "O");
        Game game = new Game(board, player);

        String status = game.play();

        assertThat(status, is("Draw"));
    }


    @Test
    public void gameIsWonWhenPlayerMakesWinningMove() throws IOException {
        Board board = new Board("X", "X", "-", "O", "X", "X", "X", "O", "O");
        Prompt prompt = new FakePrompt("2");
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Game game = new Game(board, player);

        String status = game.play();

        assertThat(status, is("Win"));
    }

}