package ttt.ui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PlainFormatterTest {

    BoardDisplay boardDisplay = new PlainBoard();

    @Test
    public void formatEmptyBoard() {

        String formattedBoard = boardDisplay.formatForDisplay(new Board(3));

        assertThat(formattedBoard, is("---------"));
    }

    @Test
    public void formatBoardWithMoves() {

        String formattedBoard = boardDisplay.formatForDisplay(
                new Board(
                        X, X, O,
                        VACANT, VACANT, VACANT,
                        VACANT, VACANT, VACANT
                )
        );

        assertThat(formattedBoard, is("XXO------"));
    }
}
