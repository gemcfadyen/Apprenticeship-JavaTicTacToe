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
    public void readsInput() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter());

        assertThat(prompt.read(), equalTo("1"));
    }

    @Test
    public void asksUserForTheirNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.askUserForTheirMove();

        assertThat(writer.toString().endsWith("\nPlease enter the index for your next move\n"), is(true));
    }

    @Test
    public void printsNewBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String formattedGrid = CLEAR_SCREEN_ANSI_CHARACTERS + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";

        assertThat(writer.toString(), is(formattedGrid));
    }

    @Test
    public void printsBoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString(), is(CLEAR_SCREEN_ANSI_CHARACTERS + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
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

        assertThat(writer.toString().contains("3" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
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
    public void printsWinningMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printWinningMessage();

        assertThat(writer.toString().endsWith("Congratulations - There is a winner\n"), is(true));
    }

    @Test
    public void printsDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printDrawMessage();

        assertThat(writer.toString().endsWith("No winner this time\n"), is(true));
    }

    @Test
    public void printsClearScreen() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.clear();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS), is(true));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        new CommandPrompt(reader, writer);
        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS), is(true));
    }

    @Test
    public void setsFontColourWhenPromptingUser() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);
        commandPrompt.askUserForTheirMove();

        assertThat(writer.toString().startsWith(CLEAR_SCREEN_ANSI_CHARACTERS + FONT_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);
        commandPrompt.print(new Board());

        assertThat(writer.toString().startsWith(CLEAR_SCREEN_ANSI_CHARACTERS + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, new StringWriter());

        promptWhichHasExceptionOnRead.read();
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(new StringReader(""), writerWhichThrowsIOException);
        promptWhichThrowsExceptionOnWrite.printWinningMessage();
    }
}
