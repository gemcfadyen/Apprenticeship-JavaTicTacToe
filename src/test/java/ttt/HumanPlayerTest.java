package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        Prompt prompt = new CommandPrompt(new StringReader("2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);

        assertThat(player.getSymbol(), is(X));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        CommandPrompt prompt = new CommandPrompt(new StringReader("2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void playerRepromptedUntilValidNumberIsEntered() throws IOException {
        CommandPrompt prompt = new CommandPrompt(new StringReader("a\nb\n2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void playerRepromptedUntilBoardValidatesMoveAsValid() throws IOException {
        CommandPrompt prompt = new CommandPrompt(new StringReader("0\n0\n10\n-1\n2\n"), new StringWriter());
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

}
