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

        assertThat(move, is(GuiHumanPlayer.IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY));
    }

    @Test
    public void playerIsReadyToMove() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        guiHuman.setMove(3);

        assertThat(guiHuman.isReady(), is(true));
    }

    @Test
    public void whenNoMoveSetPlayerIsNotReady() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);

        assertThat(guiHuman.isReady(), is(false));
    }


    @Test
    public void onceMoveIsMadePlayerIsNotReady() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        guiHuman.setMove(4);

        guiHuman.chooseNextMoveFrom(new Board(3));

        assertThat(guiHuman.isReady(), is(false));
    }
}
