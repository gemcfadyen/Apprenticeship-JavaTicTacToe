package ttt.game;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoardFactoryTest {

    @Test
    public void creates3x3Board() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoardWithSize(3);

        assertThat(board.getRows().size(), is(3));
    }

    @Test
    public void creates4x4Board() {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.createBoardWithSize(4);

        assertThat(board.getRows().size(), is(4));
    }
}