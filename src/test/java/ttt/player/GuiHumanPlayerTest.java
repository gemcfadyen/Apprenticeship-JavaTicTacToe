package ttt.player;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.X;

public class GuiHumanPlayerTest {

    @Test
    public void playerMakesPredefinedMove() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        guiHuman.setNextMove(2);
        int move = guiHuman.chooseNextMoveFrom(new Board(3));

        assertThat(move, is(2));
    }

    @Test
    public void isNotReadyByDefault() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        assertThat(guiHuman.isReady(), is(false));
    }

    @Test
    public void isReadyToMakeMove() {
        GuiHumanPlayer guiHumanPlayer = new GuiHumanPlayer(X);
        guiHumanPlayer.setNextMove(3);

        assertThat(guiHumanPlayer.isReady(), is(true));
    }


    @Test
    public void isNotReadyAnymoreAfterAMove() {
        GuiHumanPlayer guiHuman = new GuiHumanPlayer(X);
        guiHuman.setNextMove(2);
        guiHuman.chooseNextMoveFrom(new Board(3));

        assertThat(guiHuman.isReady(), is(false));
    }
}
