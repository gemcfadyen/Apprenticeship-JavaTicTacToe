package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import static ttt.Board.BOARD_DIMENSION;
import static ttt.PlayerSymbol.VACANT;

public class CommandPrompt implements Prompt {
    private static final String CLEAR_SCREEN_ANSII_CHARACTERS = "\033[H\033[2J";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String BOARD_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;34m";
    private BufferedReader reader;
    private Writer writer;

    public CommandPrompt(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;
        clear();
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
        display(FONT_COLOUR_ANSII_CHARACTERS
                + newLine()
                + "Please enter the index for your next move" + newLine());
    }

    @Override
    public void print(Board board) {
        String boardForDisplay = BOARD_COLOUR_ANSII_CHARACTERS + newLine();

        Cell[][] rows = board.getRows();
        for (Cell[] row : rows) {
            for (Cell cell : row) {
                int cellOffset = cell.getOffset();
                boardForDisplay +=
                          space()
                        + displayCell(board, cellOffset)
                        + getBorderFor(cellOffset);
            }

        }

        display(boardForDisplay);
    }

    @Override
    public void printWinningMessage() {
        display("Congratulations - There is a winner\n");
    }

    @Override
    public void printDrawMessage() {
        display("No winner this time\n");
    }

    @Override
    public void clear() {
        display(CLEAR_SCREEN_ANSII_CHARACTERS);
    }

    private void display(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private String displayCell(Board board, int cellOffset) {
        if (board.getSymbolAt(cellOffset) == VACANT) {
            return colour(cellOffset);
        } else {
            return board.getSymbolAt(cellOffset).getSymbolForDisplay();
        }
    }

    private String colour(int cellOffset) {
        return NUMBER_COLOUR_ANSII_CHARACTERS + String.valueOf(cellOffset);
    }

    private String getBorderFor(int position) {
        String border;
        if (lastRow(position)) {
            border = space() + newLine();
        } else if (endOfRow(position)) {
            border = dividingHorizontalLine();
        } else {
            border = space() + dividingVerticalLine();
        }
        return BOARD_COLOUR_ANSII_CHARACTERS + border;
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
        return index == BOARD_DIMENSION * BOARD_DIMENSION;
    }

    private boolean endOfRow(int index) {
        return index % BOARD_DIMENSION == 0;
    }
}
