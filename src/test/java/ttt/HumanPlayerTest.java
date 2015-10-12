package ttt;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        Prompt prompt = new FakePrompt("2");
        HumanPlayer player = new HumanPlayer(prompt, "X");

        assertThat(player.getSymbols(), is("X"));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        FakePrompt prompt = new FakePrompt("1");
        HumanPlayer player = new HumanPlayer(prompt, "X");
        Board board = new Board("X", "-", "X", "O", "X", "O", "X", "O");
        int indexOfMove = player.chooseNextMoveFrom(board);

        assertThat(indexOfMove, is(1));
    }
}
