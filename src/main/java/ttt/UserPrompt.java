package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by Georgina on 12/10/15.
 */
public class UserPrompt implements Prompt {
    private BufferedReader reader;
    private Writer writer;

    public UserPrompt(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
    }

    @Override
    public void askUserForTheirMove() throws IOException {
        String promptForUser = "\nPlease enter the index for your next move\n";
        display(promptForUser);
    }

    private void display(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public String read() throws IOException {
        return reader.readLine();
    }

    @Override
    public void print(Board board) throws IOException {
        StringBuffer boardForDisplay = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            boardForDisplay.append(board.getSymbolAt(i) + " ");
            boardForDisplay.append(optionallyAddNewLine(i));
        }

        display(boardForDisplay.toString());
    }

    private String optionallyAddNewLine(int i) {
        if (endOfRow(i)) {
            return ("\n");
        }
        return "";
    }

    private boolean endOfRow(int i) {
        return (i+1)  % 3 == 0;
    }

    public void printWinningMessage() throws IOException {
        display("Congratulations - There is a winner");
    }
}
