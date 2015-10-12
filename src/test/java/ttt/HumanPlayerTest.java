package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        Prompt prompt = new UserPrompt(new StringReader("2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, "X");

        assertThat(player.getSymbols(), is("X"));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Board board = new Board("X", "-", "X", "O", "X", "O", "X", "O", "-");

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedUntilValidNumberIsEntered() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("a\nb\n1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Board board = new Board("X", "-", "X", "O", "X", "O", "X", "O", "-");

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedIfTheyChooseAnOccupiedSpaceOnTheBoard() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("0\n0\n1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Board board = new Board("X", "-", "X", "O", "X", "O", "X", "O", "-");

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedIfTheyChooseASpaceOutsideOfTheBoard() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("10\n4\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Board board = new Board("X", "-", "X", "O", "-", "O", "X", "O", "-");

        assertThat(player.chooseNextMoveFrom(board), is(4));
    }
}
