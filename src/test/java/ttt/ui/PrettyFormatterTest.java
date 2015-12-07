package ttt.ui;

import org.junit.Test;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PrettyFormatterTest {
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String X_COLOUR = "\033[1;33m";
    private static final String O_COLOUR = "\033[1;31m";
    private final DisplayFormatter formatter = new PrettyFormatter();

    @Test
    public void printsNew3x3Board() {
        String formattedBoard = formatter.formatForDisplay(new Board(3));

        assertThat(formattedBoard, is(vacant3x3Board()));
    }

    @Test
    public void printsNew4x4Board() {
        String formattedBoard = formatter.formatForDisplay(new Board(4));

        assertThat(formattedBoard, is(vacant4x4Board()));
    }

    @Test
    public void colourChangesForSymbolOnBoard() {
        String formattedBoard = formatter.formatForDisplay(
                new Board(
                        VACANT, VACANT, VACANT,
                        O, VACANT, VACANT,
                        VACANT, VACANT, VACANT)
        );

        assertThat(formattedBoard.contains(3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        String formattedBoard = formatter.formatForDisplay(new Board(3));

        assertThat(formattedBoard.contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void colourChangesForGrid() {
        String formattedBoard = formatter.formatForDisplay(
                new Board(
                        VACANT, VACANT, VACANT,
                        O, VACANT, VACANT,
                        VACANT, VACANT, VACANT)
        );

        assertThat(formattedBoard.contains(NUMBER_COLOUR_ANSII_CHARACTERS), is(true));
        assertThat(formattedBoard.contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void prints3x3BoardWithMoves() {
        String formattedBoard = formatter.formatForDisplay(
                new Board(
                        VACANT, X, X,
                        O, VACANT, VACANT,
                        VACANT, VACANT, VACANT)
        );

        assertThat(formattedBoard, is(formatted3x3BoardWithMoves()));
    }

    @Test
    public void prints4x4BoardWithMoves() {
        String formattedBoard = formatter.formatForDisplay(
                new Board(
                        VACANT, VACANT, VACANT, VACANT,
                        O, VACANT, VACANT, VACANT,
                        VACANT, VACANT, VACANT, X,
                        VACANT, VACANT, VACANT, VACANT)
        );

        assertThat(formattedBoard, is(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + formatted4x4BoardWithMoves()));
    }

    @Test
    public void formatsWinningMessage() {
        String formattedWinningMessage = formatter.formatWinningMessage(X);

        String expectedMessage = FONT_COLOUR_ANSII_CHARACTERS
                + "Congratulations - "
                + X_COLOUR + "X"
                + FONT_COLOUR_ANSII_CHARACTERS
                + " has won";
        assertThat(formattedWinningMessage, is(expectedMessage));
    }

    @Test
    public void formatsBoardDimensionMessage() {
        String formattedBoardDimensionMessage = formatter.formatBoardDimensionMessage(3);

        assertThat(formattedBoardDimensionMessage, is(FONT_COLOUR_ANSII_CHARACTERS
                + "Please enter the dimension of the board you would like to use [1 to 3]"));
    }

    @Test
    public void coloursText() {
        String formattedMessage = formatter.applyFontColour("Reason");

        assertThat(formattedMessage, is(FONT_COLOUR_ANSII_CHARACTERS + "Reason"));
    }

    @Test
    public void coloursInvalidText() {
        String formattedMessage = formatter.applyInvalidColour("Reason");

        assertThat(formattedMessage, is(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "Reason"));
    }

    private String vacant3x3Board() {
        return BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n "
                + " " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n "
                + " " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " ";
    }

    private String vacant4x4Board() {
        return BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 10 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 11 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 12 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 13 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 14 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 15 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 16 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " ";
    }

    private String formatted3x3BoardWithMoves() {
        return BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " ";
    }

    private String formatted4x4BoardWithMoves() {
        return "\n" +
                "  " + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + O_COLOUR + PlayerSymbol.O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 10 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 11 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + PlayerSymbol.X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 13 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 14 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 15 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 16 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " ";
    }

}
