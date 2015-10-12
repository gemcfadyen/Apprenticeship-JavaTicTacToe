package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 09/10/15.
 */
public class GameTest {

    @Test
    public void gameIsWonWhenPlayerMakesWinningMove() throws IOException {
        Board board = new Board("X", "X", "-", "O", "X", "X", "X", "O", "O");
        Prompt mockPromptForPlayerOne = new UserPrompt(new StringReader("2\n"), new StringWriter());
        Prompt mockPromptForPlayerTwo = new UserPrompt(new StringReader("z\n"), new StringWriter());
        Prompt mockGamePrompt = new UserPrompt(new StringReader(""), new StringWriter());
        HumanPlayer playerOne = new HumanPlayer(mockPromptForPlayerOne, "X");
        HumanPlayer playerTwo = new HumanPlayer(mockPromptForPlayerTwo, "X");
        Game game = new Game(board, mockGamePrompt, playerOne, playerTwo);

        String status = game.play();

        assertThat(status, is("Win"));
    }

    @Test
    public void gameIsDrawnWhenPlayersHaveMadeFinalMovesAndNoWinningCombinationsFound() throws IOException {
        Board board = new Board("-", "O", "-", "O", "X", "X", "X", "-", "O");
        Prompt mockPromptForPlayerOne = new UserPrompt(new StringReader("7\n0\n"), new StringWriter());
        Prompt mockPromptForPlayerTwo = new UserPrompt(new StringReader("2\n"), new StringWriter());
        Prompt mockGamePrompt = new UserPrompt(new StringReader(""), new StringWriter());

        HumanPlayer playerOne = new HumanPlayer(mockPromptForPlayerOne, "X");
        HumanPlayer playerTwo = new HumanPlayer(mockPromptForPlayerTwo, "O");
        Game game = new Game(board, mockGamePrompt, playerOne, playerTwo);

        String status = game.play();

        assertThat(status, is("Draw"));
        assertThat(board.getSymbolAt(2), is("O"));
        assertThat(board.getSymbolAt(7), is("X"));
        assertThat(board.getSymbolAt(0), is("X"));
    }
}