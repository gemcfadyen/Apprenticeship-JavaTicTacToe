package ttt.ui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PlainFormatterTest {

    BoardFormatter boardFormatter = new PlainFormatter();

    @Test
    public void formatEmptyBoard() {
        Board board = new Board(3);

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard, is("---------"));
    }

    @Test
    public void formatBoardWithMoves() {
        Board board = new Board(X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard, is("XXO------"));
    }

    @Test
    public void formatsWinningMessage() {
        String formattedMessage = boardFormatter.formatWinningMessage(X);

        assertThat(formattedMessage, is("Congratulations - X has won"));
    }

    @Test
    public void formatsPlayAgainMessage() {
        String formattedMessage = boardFormatter.formatPlayAgainMessage();

        assertThat(formattedMessage, is("Play again? [Y/N]"));
    }

    @Test
    public void formatsDrawMessage() {
        String formattedMessage = boardFormatter.formatDrawMessage();
        assertThat(formattedMessage, is("No winner this time"));
    }
}
