package ttt;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        Prompt prompt = new UserPrompt(new StringReader("2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);

        assertThat(player.getSymbols(), is(X));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedUntilValidNumberIsEntered() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("a\nb\n1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedIfTheyChooseAnOccupiedSpaceOnTheBoard() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("0\n0\n1\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void playerRepromptedIfTheyChooseASpaceOutsideOfTheBoard() throws IOException {
        UserPrompt prompt = new UserPrompt(new StringReader("10\n4\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, VACANT, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(4));
    }
}
