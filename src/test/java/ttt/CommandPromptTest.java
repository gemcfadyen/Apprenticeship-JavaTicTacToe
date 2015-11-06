package ttt;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;


public class CommandPromptTest {
    private static final String CLEAR_SCREEN_ANSI_CHARACTERS = "\033[H\033[2J";
    public static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;34m";
    private static final String BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";

    @Test
    public void asksUserForTheirNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.askUserForTheirMove();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "Please enter the index for your next move\n"));
    }

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().contains(vacantBoard()), is(true));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().endsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + vacantBoard() + "\n[a] is not a valid number. Please re-enter a numeric value\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter());

        assertThat(prompt.getNextMove(new Board()), equalTo(0));
    }


    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        StringReader reader = new StringReader("a\nz\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board()), equalTo(0));
        assertThat(writer.toString().contains("[a] is not a valid number. Please re-enter a numeric value"), is(true));
        assertThat(writer.toString().contains("[z] is not a valid number. Please re-enter a numeric value"), is(true));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        StringReader reader = new StringReader("100\n-100\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board()), equalTo(0));

        assertThat(writer.toString().contains("[100] is outside of the grid boundary. Please re-enter a valid number within the grid boundary"), is(true));
        assertThat(writer.toString().contains("[-100] is outside of the grid boundary. Please re-enter a valid number within the grid boundary"), is(true));
    }

    @Test
    public void repromptsWhenMoveIsAlreadyOccupied() {
        StringReader reader = new StringReader("1\n2\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT)), equalTo(1));

        assertThat(writer.toString().contains("[1] is already occupied. Please re-enter a valid number within the grid boundary"), is(true));
    }

    @Test
    public void askTheUserToPlayAgain() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.askUserToPlayAgain();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "Play again? [Y/N]\n"));
    }

    @Test
    public void readsUsersReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y"), new StringWriter());
        assertThat(prompt.readReplayOption(), is("Y"));
    }

    @Test
    public void printsNewBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String formattedGrid = vacantBoard();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + formattedGrid));
    }

    private String vacantBoard() {
        return "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";
    }

    @Test
    public void printsBoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | X" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | X" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + "O" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"));
    }

    @Test
    public void colourChangesForSymbolOnBoard() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString().contains(3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void colourChangesForGrid() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString().contains(NUMBER_COLOUR_ANSII_CHARACTERS), is(true));
        assertThat(writer.toString().contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void printsWinner() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printWinningMessageFor(X);

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "Congratulations - X has won\n"));
    }

    @Test
    public void printsDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printDrawMessage();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "No winner this time\n"));
    }

    @Test
    public void printsClearScreen() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.clear();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"
                + "\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        new CommandPrompt(reader, writer);
        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPromptingUser() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);

        commandPrompt.askUserForTheirMove();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "Please enter the index for your next move\n"));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);
        commandPrompt.print(new Board());

        assertThat(writer.toString().startsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n"), is(true));
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingNextMoveFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, new StringWriter());

        promptWhichHasExceptionOnRead.getNextMove(new Board());
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(new StringReader(""), writerWhichThrowsIOException);
        promptWhichThrowsExceptionOnWrite.printWinningMessageFor(X);
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingRetryOptionFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, new StringWriter());

        promptWhichHasExceptionOnRead.readReplayOption();
    }
}
