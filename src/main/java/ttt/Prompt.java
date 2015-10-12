package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 12/10/15.
 */
public interface Prompt {
    String read() throws IOException;
    void askUserForTheirMove() throws IOException;
    void print(Board board) throws IOException;
    void printWinningMessage() throws IOException;
    void printDrawMessage() throws IOException;
}
