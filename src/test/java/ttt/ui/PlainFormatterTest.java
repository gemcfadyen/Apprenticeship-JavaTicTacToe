package ttt.ui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PlainFormatterTest {

    @Test
    public void formatEmptyBoard() {
        Board board = new Board(3);
        BoardFormatter boardFormatter = new PlainFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard, is("---------"));
    }

    @Test
    public void formatBoardWithMoves() {
        Board board = new Board(X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        BoardFormatter boardFormatter = new PlainFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard, is("XXO------"));
    }
}
