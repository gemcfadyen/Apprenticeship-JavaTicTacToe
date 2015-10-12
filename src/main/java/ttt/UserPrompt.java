package ttt;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Georgina on 12/10/15.
 */
public class UserPrompt implements Prompt {
    private Reader reader;

    public UserPrompt(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void display(String message) {

    }

    @Override
    public String read() throws IOException {
        char input = (char) reader.read();
        return String.valueOf(input);
    }
}
