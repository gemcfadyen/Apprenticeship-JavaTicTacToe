package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import static ttt.Board.BOARD_DIMENSION;

public class CommandPrompt implements Prompt {
    private BufferedReader reader;
    private Writer writer;

    public CommandPrompt(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
    }

    @Override
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromPromptException(e.getMessage(), e);
        }
    }

    @Override
    public void askUserForTheirMove() {
        display("\nPlease enter the index for your next move\n");
    }


    @Override
    public void print(Board board) {
        String boardForDisplay = newLine();

        for (int boardPosition = 0; boardPosition < BOARD_DIMENSION * BOARD_DIMENSION; boardPosition++) {
            boardForDisplay += space() + getSymbol(board, boardPosition) + getBorderFor(boardPosition);
        }

        display(boardForDisplay);
    }

    private String getSymbol(Board board, int position) {
        if (board.getSymbolAt(position) == PlayerSymbol.VACANT) {
            return String.valueOf(position + 1);
        } else {
            return board.getSymbolAt(position).getSymbolForDisplay();
        }
    }

    @Override
    public void printWinningMessage() {
        display("Congratulations - There is a winner\n");
    }

    @Override
    public void printDrawMessage() {
        display("No winner this time\n");
    }

    private void display(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private String getBorderFor(int position) {
        if (lastRow(position)) {
            return space() + newLine();
        }
        if (endOfRow(position)) {
            return dividingHorizontalLine();
        }
        return space() + dividingVerticalLine();
    }

    private String dividingVerticalLine() {
        return "|";
    }

    private String dividingHorizontalLine() {
        return space() + newLine() + "-----------" + newLine();
    }

    private String newLine() {
        return "\n";
    }

    private String space() {
        return " ";
    }

    private boolean lastRow(int index) {
        return index == BOARD_DIMENSION * BOARD_DIMENSION - 1;
    }

    private boolean endOfRow(int i) {
        return (i + 1) % BOARD_DIMENSION == 0;
    }
}
