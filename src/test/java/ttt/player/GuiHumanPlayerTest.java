package ttt.player;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.X;

public class GuiHumanPlayerTest {

    @Test
    public void playerMakesNoMove() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);

        int move = guiHuman.chooseNextMoveFrom(new Board(3));

        assertThat(move, is(GuiHumanPlayer.FAKE_MOVE));
    }
}
