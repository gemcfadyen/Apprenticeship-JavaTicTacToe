package ttt.guiapp;

import org.junit.Test;
import ttt.game.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.X;

public class GuiHumanPlayerTest {

    @Test
    public void playerIsReadyToMove() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        guiHuman.update(3);

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
        guiHuman.update(4);

        guiHuman.chooseNextMoveFrom(new Board(3));

        assertThat(guiHuman.isReady(), is(false));
    }
}
