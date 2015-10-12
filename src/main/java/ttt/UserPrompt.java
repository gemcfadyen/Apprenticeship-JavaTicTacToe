package ttt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by Georgina on 12/10/15.
 */
public class UserPrompt implements Prompt {
    private Reader reader;
    private Writer writer;

    public UserPrompt(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void askUserForTheirMove() throws IOException {
        String promptForUser = "\nPlease enter the index for your next move\n";
        display(promptForUser);
    }

    private String display(String message) throws IOException {
        writer.write(message);
        writer.flush();
        return message;
    }

    @Override
    public String read() throws IOException {
        char input = (char) reader.read();
        while (String.valueOf(input).equals("\n")) {
            input = (char) reader.read();
        }
        return String.valueOf(input);
    }

    @Override
    public String print(Board board) throws IOException {
        StringBuffer boardForDisplay = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            boardForDisplay.append(board.getSymbolAt(i) + " ");
            boardForDisplay.append(optionallyAddNewLine(i));
        }

        return display(boardForDisplay.toString());
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
}
