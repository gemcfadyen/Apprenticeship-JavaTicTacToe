package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 12/10/15.
 */
public interface Prompt {
    void display(String message);

    int read() throws IOException;
}
