package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class GameTest {

    @Test
    public void gameIsWonWhenPlayerMakesWinningMove() throws IOException {
        Board board = new Board("X", "X", "-", "O", "X", "X", "X", "O", "O");
        Prompt promptForPlayerOne = new UserPrompt(new StringReader("2"));
        Prompt promptForPlayerTwo = new UserPrompt(new StringReader("z"));
        HumanPlayer playerOne = new HumanPlayer(promptForPlayerOne, "X");
        HumanPlayer playerTwo = new HumanPlayer(promptForPlayerTwo, "X");
        Game game = new Game(board, playerOne, playerTwo);

        String status = game.play();

        assertThat(status, is("Win"));
    }

    @Test
    public void gameIsDrawnWhenPlayersHaveMadeFinalMovesAndNoWinningCombinationsFound() throws IOException {
        Board board = new Board("-", "O", "-", "O", "X", "X", "X", "-", "O");
        Prompt promptForPlayerOne = new UserPrompt(new StringReader("70"));
        Prompt promptForPlayerTwo = new UserPrompt(new StringReader("2"));

        HumanPlayer playerOne = new HumanPlayer(promptForPlayerOne, "X");
        HumanPlayer playerTwo = new HumanPlayer(promptForPlayerTwo, "O");
        Game game = new Game(board, playerOne, playerTwo);

        String status = game.play();

        assertThat(status, is("Draw"));
        assertThat(board.getSymbolAt(2), is("O"));
        assertThat(board.getSymbolAt(7), is("X"));
        assertThat(board.getSymbolAt(0), is("X"));
    }
}