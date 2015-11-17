package ttt.player;

import org.junit.Test;
import ttt.PromptSpy;
import ttt.board.Board;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        PromptSpy prompt = new PromptSpy(new StringReader(""));
        HumanPlayer player = new HumanPlayer(X, prompt);

        assertThat(player.getSymbol(), is(X));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        PromptSpy prompt = new PromptSpy(new StringReader("1\n"));
        HumanPlayer player = new HumanPlayer(X, prompt);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }
}
