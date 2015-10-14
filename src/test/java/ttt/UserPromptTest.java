package ttt;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class UserPromptTest {
    @Test
    public void displaysBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new UserPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedDisplay = " 0  1  2 \n 3  4  5 \n 6  7  8 \n\n -  -  - \n -  -  - \n -  -  - \n";
        assertThat(writer.toString(), is(expectedDisplay));
    }

    @Test
    public void displaysWinningMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        UserPrompt prompt = new UserPrompt(reader, writer);

        prompt.printWinningMessage();

        assertThat(writer.toString(), is("Congratulations - There is a winner"));
    }

    @Test
    public void displaysDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        UserPrompt prompt = new UserPrompt(reader, writer);

        prompt.printDrawMessage();

        assertThat(writer.toString(), is("No winner this time"));
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new UserPrompt(readerWhichThrowsIOException, new StringWriter());

        promptWhichHasExceptionOnRead.read();
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new UserPrompt(new StringReader(""), writerWhichThrowsIOException);
        promptWhichThrowsExceptionOnWrite.printWinningMessage();
    }
}
