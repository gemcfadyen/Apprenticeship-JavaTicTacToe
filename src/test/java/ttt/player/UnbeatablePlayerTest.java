package ttt.player;

import org.junit.Test;
import ttt.board.Board;
import ttt.ui.CommandPrompt;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class UnbeatablePlayerTest {

    @Test
    public void takesWinningMoveOnTopRow() {
        Board board = new Board(
                VACANT, X, X,
                VACANT, O, VACANT,
                O, VACANT, VACANT);

        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(0));
    }

    @Test
    public void takesWinningMoveOnMiddleRow() {
        Board board = new Board(
                VACANT, VACANT, VACANT,
                X, X, VACANT,
                O, VACANT, O);

        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(5));
    }

    @Test
    public void takesWinningMoveOnBottomRow() {
        Board board = new Board(
                VACANT, O, VACANT,
                VACANT, O, VACANT,
                X, VACANT, X);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(7));
    }

    @Test
    public void takesWinningMoveOnLeftColumn() {
        Board board = new Board(
                X, VACANT, O,
                X, VACANT, O,
                VACANT, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(6));
    }

    @Test
    public void takesWinningMoveOnMiddleColumn() {
        Board board = new Board(
                VACANT, X, VACANT,
                O, VACANT, O,
                VACANT, X, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(4));
    }

    @Test
    public void takesWinningMoveOnRightColumn() {
        Board board = new Board(
                VACANT, VACANT, X,
                O, VACANT, X,
                VACANT, O, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(8));
    }


    @Test
    public void takesWinningMoveOnForwardDiagonal() {
        Board board = new Board(
                VACANT, VACANT, X,
                O, VACANT, O,
                X, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(4));
    }

    @Test
    public void takesWinningMoveBackwardsDiagonal() {
        Board board = new Board(
                X, VACANT, VACANT,
                O, X, O,
                VACANT, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(8));
    }

    @Test
    public void blocksOpponentsWinningMoveOnRightColumn() {
        Board board = new Board(
                X, X, O,
                VACANT, VACANT, O,
                VACANT, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(8));
    }

    @Test
    public void blocksOpponentsWinningMoveOnMiddleColumn() {
        Board board = new Board(
                X, VACANT, VACANT,
                VACANT, O, X,
                VACANT, O, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void blocksOpponentsWinningMoveOnLeftColumn() {
        Board board = new Board(
                O, VACANT, VACANT,
                VACANT, VACANT, X,
                O, X, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(3));
    }

    @Test
    public void blocksOpponentsWinningMoveOnTopRow() {
        Board board = new Board(
                O, VACANT, O,
                X, VACANT, VACANT,
                VACANT, X, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void blocksOpponentsWinningMoveOnBottomRow() {
        Board board = new Board(
                VACANT, VACANT, X,
                VACANT, VACANT, X,
                O, VACANT, O);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(7));
    }

    @Test
    public void blocksOpponentsWinningMoveOnMiddleRow() {
        Board board = new Board(
                X, VACANT, VACANT,
                O, O, VACANT,
                X, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(5));
    }

    @Test
    public void blocksOpponentsWinningMoveOnDiagonal() {
        Board board = new Board(
                O, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, O);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(4));
    }

    @Test
    public void blocksOpponentsWinningMoveOnOtherDiagonal() {
        Board board = new Board(
                X, VACANT, VACANT,
                VACANT, O, VACANT,
                O, X, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void blocksFork() {
        Board board = new Board(
                O, VACANT, VACANT,
                VACANT, X, VACANT,
                VACANT, VACANT, O);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(1));
    }

    @Test
    public void blocksCornerTrap() {
        Board board = new Board(
                X, O, VACANT,
                VACANT, VACANT, O,
                VACANT, VACANT, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(6));
    }

    @Test
    public void walkThrough() {
        Board board = new Board(
                X, O, X,
                O, O, VACANT,
                VACANT, X, VACANT);
        UnbeatablePlayer player = new UnbeatablePlayer(new CommandPrompt(new StringReader(""), new StringWriter()), X);

        assertThat(player.chooseNextMoveFrom(board), is(5));
    }
}