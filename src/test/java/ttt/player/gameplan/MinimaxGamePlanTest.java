package ttt.player.gameplan;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class MinimaxGamePlanTest {

    @Test
    public void winForMaxPlayerScores10() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board boardWithWinForX = new Board(X, X, X, O, VACANT, O, VACANT, VACANT, VACANT);

        int score = gamePlan.execute(boardWithWinForX, X, 6, true);

        assertThat(score, is(4));
    }

    @Test
    public void winForMinPlayerScoresNegative10() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board boardWithWinForX = new Board(O, O, O, X, VACANT, X, VACANT, VACANT, VACANT);

        int score = gamePlan.execute(boardWithWinForX, X, 6, true);

        assertThat(score, is(-4));
    }

    @Test
    public void drawScores0() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(X, O, X, X, X, O, O, X, O);
        int score = gamePlan.execute(board, X, 0, true);

        assertThat(score, is(0));
    }

    @Test
    public void scores9ForVacantCellThatWillResultInWinForMaximisingPlayer() {
        MinimaxGamePlan gamePlan = new MinimaxGamePlan();
        Board board = new Board(X, O, O,
                X, X, VACANT,
                O, X, O);
        int score = gamePlan.execute(board, X, 1, true);

        assertThat(score, is(9));
    }

}
