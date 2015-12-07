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
    public void formatsBoardDimensionPrompt() {
        String formattedMessage = boardFormatter.formatBoardDimensionMessage(5);
        assertThat(formattedMessage, is("Please enter the dimension of the board you would like to use [1 to 5]"));
    }

    @Test
    public void formatsInvalidReason(){
        String formattedMessage = boardFormatter.applyInvalidColour("Invalid");

        assertThat(formattedMessage, is("Invalid"));
    }
}
