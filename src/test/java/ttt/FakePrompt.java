package ttt;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Georgina on 12/10/15.
 */
public class FakePrompt implements Prompt {
    private StringReader reader;
    private String positionOfNextMove;

    public FakePrompt(String positionOfNextMove) {
        this.positionOfNextMove = positionOfNextMove;
    }

    @Override
    public void display(String message) {

    }

    @Override
    public int read() throws IOException {
        reader = new StringReader(positionOfNextMove);
        char input = (char)reader.read();

        return Integer.valueOf(String.valueOf(input));
    }
}
