package ttt.game.players;

import org.junit.Test;
import ttt.game.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.O;
import static ttt.game.PlayerSymbol.VACANT;
import static ttt.game.PlayerSymbol.X;

public class DelayedUnbeatablePlayerTest {

    @Test
    public void takesFirstVacantPositionFromEmptyBoard() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(new Board(4)), is(0));
    }

    @Test
    public void takesFirstVacantPositionFromBoardWithTwoPositionsTaken() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                X, O, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void takesWinningMoveInFirstRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                O, O, O, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(3));
    }

    @Test
    public void takesWinningMoveInSecondRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                VACANT, X, X, X,
                O, O, O, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(4));
    }

    @Test

    public void takesWinningMoveInThirdRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                O, O, O, VACANT,
                VACANT, X, X, X,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(8));
    }

    @Test
    public void takesWinningMoveInFourthRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                O, O, O, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                X, X, VACANT, X
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(14));
    }

    @Test
    public void takesWinningMoveInFirstColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT,
                VACANT, O, O, VACANT,
                X, VACANT, VACANT, O
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(8));
    }


    @Test
    public void takesWinningMoveInSecondColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, X, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, X, O, VACANT,
                O, X, VACANT, O
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(5));
    }


    @Test
    public void takesWinningMoveInThirdColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, X, VACANT,
                VACANT, VACANT, X, VACANT,
                VACANT, O, X, VACANT,
                O, O, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(14));
    }

    @Test
    public void takesWinningMoveInFourthColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, X,
                VACANT, O, VACANT, X,
                O, O, VACANT, X
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(3));
    }

    @Test
    public void takesWinningMoveInForwardDiagonal() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, X,
                VACANT, VACANT, X, VACANT,
                VACANT, X, O, VACANT,
                VACANT, O, O, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(12));
    }

    @Test
    public void takesWinningMoveInBackwardsDiagonal() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                X, VACANT, VACANT, VACANT,
                VACANT, X, VACANT, VACANT,
                VACANT, O, X, VACANT,
                VACANT, O, O, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(15));
    }

    @Test
    public void blocksOpponentsWinInFirstRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                O, O, O, VACANT,
                VACANT, X, VACANT, VACANT,
                VACANT, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(3));
    }

    @Test
    public void blocksOpponentsWinInSecondRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, X, VACANT, VACANT,
                O, O, VACANT, O,
                VACANT, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(6));
    }

    @Test
    public void blocksOpponentsWinInThirdRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, X, X, O,
                VACANT, X, X, VACANT,
                O, VACANT, O, O,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(9));
    }

    @Test
    public void blocksOpponentsWinInFourthRow() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, X, X, O,
                VACANT, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                O, VACANT, O, O
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(13));
    }


    @Test
    public void blocksOpponentWinInFirstColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                O, X, X, VACANT,
                O, VACANT, X, VACANT,
                VACANT, VACANT, O, VACANT,
                O, X, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(8));
    }


    @Test
    public void blocksOpponentsWinInThirdColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, O, VACANT,
                VACANT, X, O, VACANT,
                VACANT, X, O, VACANT,
                X, VACANT, VACANT, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(14));
    }

    @Test
    public void blocksOpponentsWinInFourthColumn() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, VACANT, O,
                VACANT, X, VACANT, VACANT,
                X, X, VACANT, O
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(11));
    }

    @Test
    public void blocksOpponentsWinInForwardDiagonal() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, O, VACANT,
                VACANT, O, X, VACANT,
                VACANT, X, X, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(12));
    }

    @Test
    public void blocksOpponentsWinInBackwardsDiagonal() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                VACANT, O, VACANT, VACANT,
                VACANT, X, O, VACANT,
                VACANT, X, X, O
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(0));
    }

    @Test
    public void blocksOpponentWhenUnbeatableDoesNotOpenTheGame() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                X, X, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                O, O, O, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(15));
    }

    @Test
    public void blocksOpponentFromForkingWhenUnbeatableDoesNotOpenTheGame() {
        DelayedUnbeatablePlayer delayedUnbeatablePlayer = new DelayedUnbeatablePlayer(X, new UnbeatablePlayer(X));

        Board board = new Board(
                O, O, O, X,
                VACANT, O, O, VACANT,
                X, X, O, X,
                X, VACANT, X, VACANT
        );
        assertThat(delayedUnbeatablePlayer.chooseNextMoveFrom(board), is(15));
    }
}
