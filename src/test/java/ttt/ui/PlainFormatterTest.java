package ttt.ui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PlainFormatterTest {

    DisplayFormatter displayFormatter = new PlainFormatter();

    @Test
    public void formatEmptyBoard() {

        String formattedBoard = displayFormatter.formatForDisplay(new Board(3));

        assertThat(formattedBoard, is("---------"));
    }

    @Test
    public void formatBoardWithMoves() {

        String formattedBoard = displayFormatter.formatForDisplay(
                new Board(
                        X, X, O,
                        VACANT, VACANT, VACANT,
                        VACANT, VACANT, VACANT
                )
        );

        assertThat(formattedBoard, is("XXO------"));
    }

    @Test
    public void formatsWinningMessage() {
        String formattedMessage = displayFormatter.formatWinningMessage(X);

        assertThat(formattedMessage, is("Congratulations - X has won"));
    }

    @Test
    public void formatsBoardDimensionPrompt() {
        String formattedMessage = displayFormatter.formatBoardDimensionMessage(5);
        assertThat(formattedMessage, is("Please enter the dimension of the board you would like to use [1 to 5]"));
    }

    @Test
    public void formatsInvalidReason() {
        String formattedMessage = displayFormatter.applyInvalidColour("Invalid");

        assertThat(formattedMessage, is("Invalid"));
    }
}
