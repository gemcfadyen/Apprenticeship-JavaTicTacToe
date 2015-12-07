package ttt.ui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class PrettyFormatterTest {
    private static final String CLEAR_SCREEN_ANSI_CHARACTERS = "\033[H\033[2J";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String X_COLOUR = "\033[1;33m";
    private static final String O_COLOUR = "\033[1;31m";

    @Test
    public void printsNew3x3Board() {
        Board board = new Board(3);
        BoardFormatter formatter = new PrettyFormatter();

        String formattedBoard = formatter.formatForDisplay(board);

        String expectedFormat = vacant3x3Board();

        assertThat(formattedBoard, is(expectedFormat));
    }

    @Test
    public void printsNew4x4Board() {
        Board board = new Board(4);
        BoardFormatter formatter = new PrettyFormatter();

        String formattedBoard = formatter.formatForDisplay(board);

        String expectedFormat = vacant4x4Board();

        assertThat(formattedBoard, is(expectedFormat));
    }

    @Test
    public void colourChangesForSymbolOnBoard() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);
        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard.contains(3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        Board board = new Board(3);
        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard.contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void colourChangesForGrid() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        assertThat(formattedBoard.contains(NUMBER_COLOUR_ANSII_CHARACTERS), is(true));
        assertThat(formattedBoard.contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void prints3x3BoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);


        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);


        assertThat(formattedBoard, is(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " "));
    }

    @Test
    public void prints4x4BoardWithMoves() {
        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                O, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, X,
                VACANT, VACANT, VACANT, VACANT);

        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedBoard = boardFormatter.formatForDisplay(board);

        String expectedFormattedBoard = "\n" +
                "  " + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 10 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 11 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 13 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 14 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 15 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 16 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " ";

        assertThat(formattedBoard, is(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + expectedFormattedBoard));
    }

    @Test
    public void formatsWinningMessage() {
        BoardFormatter boardFormatter = new PrettyFormatter();

        String formattedWinningMessage = boardFormatter.formatWinningMessage(X);

        String expectedMessage = FONT_COLOUR_ANSII_CHARACTERS
                + "Congratulations - "
                + X_COLOUR  + "X"
                + FONT_COLOUR_ANSII_CHARACTERS
                + " has won";
        assertThat(formattedWinningMessage, is(expectedMessage));
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

}
