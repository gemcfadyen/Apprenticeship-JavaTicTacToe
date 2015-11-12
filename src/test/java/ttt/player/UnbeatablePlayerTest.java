package ttt.player;

import org.junit.Test;
import ttt.board.Board;
import ttt.ui.CommandPrompt;

import java.io.StringReader;
import java.io.StringWriter;

import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class UnbeatablePlayerTest {

    @Test
    public void scores10WhenMaximisingPlayerWins() {
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, X);

        int bestMove = player.chooseNextMoveFrom(board);
//        Assert.assertThat(bestMove, is())
    }

}