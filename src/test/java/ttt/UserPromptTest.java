package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Georgina on 12/10/15.
 */
public class UserPromptTest {
    @Test
    public void displaysBoard() throws IOException {
        Board board = new Board();
        UserPrompt prompt = new UserPrompt(new StringReader(""), new StringWriter());

        String boardForDisplay = prompt.print(board);

        String expectedDisplay = "- - - \n- - - \n- - - \n";
        assertThat(boardForDisplay, is(expectedDisplay));
    }

}
