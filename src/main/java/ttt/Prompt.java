package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 12/10/15.
 */
public interface Prompt {
    void askUserForTheirMove() throws IOException;
    String read() throws IOException;
    void print(Board board) throws IOException;
    void printWinningMessage() throws IOException;
}
