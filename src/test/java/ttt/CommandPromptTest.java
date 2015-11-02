package ttt;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


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
    public void printsBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedDisplay = "\n -  -  - \n -  -  - \n -  -  - \n";
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
