package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import static ttt.Board.BOARD_DIMENSION;
import static ttt.Board.NUMBER_OF_SLOTS;

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
    public String read() throws IOException {
        return reader.readLine();
    }

    @Override
    public void askUserForTheirMove() throws IOException {
        String promptForUser = "\nPlease enter the index for your next move\n";
        display(promptForUser);
    }

    @Override
    public void print(Board board) throws IOException {
        StringBuilder boardForDisplay = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            boardForDisplay.append(board.getSymbolAt(i).getSymbolForDisplay() + " ");
            boardForDisplay.append(optionallyAddNewLine(i));
        }

        display(boardForDisplay.toString());
    }

    @Override
    public void printWinningMessage() throws IOException {
        display("Congratulations - There is a winner");
    }

    @Override
    public void printDrawMessage() throws IOException {
        display("No winner this time");
    }

    private void display(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    private String optionallyAddNewLine(int i) {
        if (endOfRow(i)) {
            return ("\n");
        }
        return "";
    }

    private boolean endOfRow(int i) {
        return (i+1)  % BOARD_DIMENSION == 0;
    }
}
