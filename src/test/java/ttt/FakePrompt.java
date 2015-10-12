package ttt;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Georgina on 12/10/15.
 */
public class FakePrompt implements Prompt {
    private StringReader reader;
    private String[] positionOfNextMoves;
    private int indexOfCurrentMove;

    public FakePrompt(String... inputs) {
        this.positionOfNextMoves = inputs;
        this.indexOfCurrentMove = 0;
    }

    @Override
    public void display(String message) {

    }

    @Override
    public String read() throws IOException {
        reader = new StringReader(positionOfNextMoves[indexOfCurrentMove]);
        indexOfCurrentMove++;
        char input = (char)reader.read();

        return String.valueOf(input);
    }
}
