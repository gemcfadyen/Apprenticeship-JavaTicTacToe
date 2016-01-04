package ttt.commandlineapp;

import org.junit.Test;
import ttt.game.Board;
import ttt.commandlineapp.PlainBoard;
import ttt.game.BoardDisplay;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.*;

public class PlainBoardTest {

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
