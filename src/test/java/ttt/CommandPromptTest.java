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


    @Test
    public void readsInput() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt =  new CommandPrompt(reader, new StringWriter());

        assertThat(prompt.read(), equalTo("1"));
    }

    @Test
    public void asksUserForTheirNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.askUserForTheirMove();

        assertThat(writer.toString(), is("\nPlease enter the index for your next move\n"));
    }

    @Test
    public void printsNewBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedDisplay =
              "\n 1 | 2 | 3 \n"
              + "-----------\n"
              + " 4 | 5 | 6 \n"
              + "-----------\n"
              + " 7 | 8 | 9 \n";

        assertThat(writer.toString(), is(expectedDisplay));
    }

    @Test
    public void printsBoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedDisplay =
                "\n 1 | X | X \n"
                + "-----------\n"
                + " O | 5 | 6 \n"
                + "-----------\n"
                + " 7 | 8 | 9 \n";

        assertThat(writer.toString(), is(expectedDisplay));

    }


    @Test
    public void printsWinningMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printWinningMessage();

        assertThat(writer.toString(), is("Congratulations - There is a winner\n"));
    }

    @Test
    public void printsDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printDrawMessage();

        assertThat(writer.toString(), is("No winner this time\n"));
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
