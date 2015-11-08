package ttt;

import java.io.*;
import java.util.Arrays;
import java.util.List;

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
    public int getNextMove(Board board) {
        print(board);
        askUserForTheirMove();
        int move = getValidMove(board);
        clear();
        return move;
    }

    @Override
    public String getReplayOption() {
        clear();
        askUserToPlayAgain();
        return validateReplay(input());
    }

    @Override
    public void print(Board board) {
        String boardForDisplay = BOARD_COLOUR_ANSII_CHARACTERS + newLine();

        Line[] rows = board.getRows();
        int offset = 0;
        for (Line row : rows) {
            for(PlayerSymbol symbol : row.getSymbols()) {
                boardForDisplay +=
                          space()
                        + displayCell(symbol, offset)
                        + getBorderFor(offset);
                offset++;
            }
        }

        display(boardForDisplay);
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Congratulations - "
                + symbol
                + " has won");
    }

    @Override
    public void printDrawMessage() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "No winner this time");
    }

    @Override
    public void clear() {
        display(CLEAR_SCREEN_ANSII_CHARACTERS);
    }

    private int getValidMove(Board board) {
        return validateMove(input(), board);
    }

    public String input() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromPromptException(e.getMessage(), e);
        }
    }

    private int validateMove(String value, Board board) {
        String input = value;
        //pass in through constructor as long as board is passed in isValid method - composite patter?
        //return aResult object contain boolean and reason and maybe zero index?
        //   allValid = obj[numberValidaor, grid boundar, isvacane];
        //   allValid = obj[numberValidaor, grid boundar, isvacane];

//        UberValidator uber = new UberValidator(number, withingrid, unoccupied);
//        Result r = uber.isValid(input, board);
//        while(!r.isValid()) {
//            clear();
//            print(board);
//            display(r.invalidReason());
//            input = input();
//            r = uber.isValid(input, board);
//        }
        while (!isValid(input, board)) {
            input = input();
        }

        return zeroIndexed(input);
    }

    private void askUserForTheirMove() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Please enter the index for your next move");
    }

    private void askUserToPlayAgain() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Play again? [Y/N]");
    }

    private String validateReplay(String input) {
        while (!isValid(input)) {
            input = input();
        }
        return input;
    }

    private boolean isValid(String input, Board board) {
        for (InputValidator moveValidator : orderedListOfMoveValidators(board)) {
            if (!moveValidator.isValid(input)) {
                clear();
                print(board);
                display(moveValidator.invalidReason(input));
                return false;
            }
        }
        return true;
    }

    private List<InputValidator> orderedListOfMoveValidators(Board board) {
        return Arrays.asList(
                new NumericValidator(),
                new WithinGridBoundaryValidator(board),
                new FreeSpaceOnBoardValidator(board));
    }

    private boolean isValid(String input) {
        for (InputValidator replayValidator : replayValidators()) {
            if (!replayValidator.isValid(input)) {
                clear();
                display(replayValidator.invalidReason(input));
                return false;
            }
        }
        return true;
    }

    private List<InputValidator> replayValidators() {
        return Arrays.asList(new ReplayOptionValidator());
    }

    private int zeroIndexed(String input) {
        return Integer.valueOf(input) - 1;
    }

    private void display(String message) {
        try {
            writer.write(newLine() + message + newLine());
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private String displayCell(PlayerSymbol symbol, int cellOffset) {
        if (symbol == VACANT) {
            return colour(cellOffset);
        } else {
            return symbol.getSymbolForDisplay();
        }
    }

    private String colour(int cellOffset) {
        return NUMBER_COLOUR_ANSII_CHARACTERS + String.valueOf(cellOffset + 1);
    }

    private String getBorderFor(int position) {
        String border;
        if (lastRow(position)) {
            border = space();
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
        return index == (BOARD_DIMENSION * BOARD_DIMENSION - 1);
    }

    private boolean endOfRow(int index) {
        return (index + 1) % BOARD_DIMENSION == 0;
    }
}
