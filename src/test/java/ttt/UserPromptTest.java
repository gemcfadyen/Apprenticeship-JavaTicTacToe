package ttt;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 12/10/15.
 */
public class UserPromptTest {
    @Test
    public void displaysBoard() throws IOException {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        UserPrompt prompt = new UserPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedDisplay = "- - - \n- - - \n- - - \n";
        assertThat(writer.toString(), is(expectedDisplay));
    }

    @Test
    public void displaysWinningMessage() throws IOException {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        UserPrompt prompt = new UserPrompt(reader, writer);

        prompt.printWinningMessage();

        assertThat(writer.toString(), is("Congratulations - There is a winner"));
    }

}
