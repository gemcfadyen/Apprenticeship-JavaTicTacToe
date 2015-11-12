package ttt.player.gameplan;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class MinimaxGamePlanTest {

    @Test
    public void experiment() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board boardWithWinForX = new Board(
                O, O, X,
                X, X, VACANT,
                O, VACANT, VACANT);

        ValuedPosition valuedPosition = gamePlan.minimax(boardWithWinForX, X, X, 3, true);

        assertThat(valuedPosition.getMove(), is(5));
    }

    @Test
    public void winForMaxPlayerScores10() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board boardWithWinForX = new Board(
                X, X, X,
                O, VACANT, O,
                VACANT, VACANT, VACANT);

        ValuedPosition valuedPosition = gamePlan.minimax(boardWithWinForX, X, X, 4, true);

        assertThat(valuedPosition.getScore(), is(14));
    }

    @Test
    public void winForMinPlayerScoresNegative10() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board boardWithWinForX = new Board(O, O, O, X, VACANT, X, VACANT, VACANT, VACANT);

        ValuedPosition valuedPosition = gamePlan.minimax(boardWithWinForX, X, X, 4, true);

        assertThat(valuedPosition.getScore(), is(-14));
    }

    @Test
    public void drawScores0() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(X, O, X, X, X, O, O, X, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 0, true);

        assertThat(valuedPosition.getScore(), is(0));
    }

    @Test
    public void takesWinningMoveOnTopRowLeft() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, X, X,
                O, VACANT, VACANT,
                O, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(0));
    }

    @Test
    public void testing() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, O, X,
                O, O, VACANT,
                VACANT, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 3, true);

        assertThat(valuedPosition.getMove(), is(5));
    }

    @Test
    public void takesWinningMoveOnTopRowMiddle() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, X,
                O, VACANT, VACANT,
                O, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(1));
    }

    @Test
    public void takesWinningMoveOnTopRowRight() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, X, VACANT,
                VACANT, O, VACANT,
                VACANT, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(2));
    }

    /********************************************/

    @Test
    public void takesWinningMoveOnMiddleRowLeft() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, VACANT,
                VACANT, X, X,
                O, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(3));
    }


    @Test
    public void takesWinningMoveOnMiddleRowRight() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, VACANT,
                X, X, VACANT,
                O, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(5));
    }


    @Test
    public void takesWinningMoveOnMiddleRowMiddle() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, VACANT,
                X, VACANT, X,
                O, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(4));
    }

    /*************************************/

    @Test
    public void takesWinningMoveOnBottomRowLeft() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, O, VACANT,
                VACANT, O, VACANT,
                VACANT, X, X);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(6));
    }

    @Test
    public void takesWinningMoveOnBottomRowRight() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, O, VACANT,
                VACANT, O, VACANT,
                X, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(8));
    }

    @Test
    public void takesWinningMoveOnBottomRowMiddle() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, O, VACANT,
                VACANT, O, VACANT,
                X, VACANT, X);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(7));
    }

    /****************/

    @Test
    public void takesWinningMoveOnDiagonal() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, X,
                O, VACANT, O,
                X, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(4));
    }

    @Test
    public void takesWinningMoveOnOtherDiagonal() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, VACANT,
                O, X, O,
                VACANT, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(8));
    }

    /*******************/

    @Test
    public void takesWinningMoveOnLeftColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, O,
                X, VACANT, O,
                VACANT, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(6));
    }

    @Test
    public void takesWinningMoveOnMiddleColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, X, VACANT,
                O, VACANT, O,
                VACANT, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(4));
    }

    @Test
    public void takesWinningMoveOnRightColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, X,
                O, VACANT, X,
                VACANT, O, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(8));
    }

    /****
     * blocks
     ***********/
    @Test
    public void blocksOpponentsWinningMoveOnRightColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, X, O,
                VACANT, VACANT, O,
                VACANT, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(8));
    }

    @Test
    public void blocksOpponentsWinningMoveOnMiddleColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, VACANT,
                VACANT, O, X,
                VACANT, O, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(1));
    }

    @Test
    public void blocksOpponentsWinningMoveOnLeftColumn() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                O, VACANT, VACANT,
                VACANT, VACANT, X,
                O, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(3));
    }

    @Test
    public void blocksOpponentsWinningMoveOnTopRow() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                O, VACANT, O,
                X, VACANT, VACANT,
                VACANT, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(1));
    }

    @Test
    public void blocksOpponentsWinningMoveOnBottomRow() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                VACANT, VACANT, X,
                VACANT, VACANT, X,
                O, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(7));
    }

    @Test
    public void blocksOpponentsWinningMoveOnMiddleRow() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, VACANT,
                O, O, VACANT,
                X, VACANT, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 4, true);

        assertThat(valuedPosition.getMove(), is(5));
    }

    @Test
    public void blocksOpponentsWinningMoveOnDiagonal() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                O, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, O);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(4));
    }

    @Test
    public void blocksOpponentsWinningMoveOnOtherDiagonal() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(
                X, VACANT, VACANT,
                VACANT, O, VACANT,
                O, X, VACANT);
        ValuedPosition valuedPosition = gamePlan.minimax(board, X, X, 5, true);

        assertThat(valuedPosition.getMove(), is(2));
    }


}
