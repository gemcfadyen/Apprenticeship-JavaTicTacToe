package ttt;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ttt.Board.BOARD_DIMENSION;
import static ttt.PlayerSymbol.*;
import static ttt.PlayerSymbol.VACANT;

public class CommandPrompt implements Prompt {
    private static final String CLEAR_SCREEN_ANSII_CHARACTERS = "\033[H\033[2J";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String BOARD_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String X_COLOUR_ANSII_CHARACTERS = "\033[1;33m";
    private static final String O_COLOUR_ANSII_CHARACTERS = "\033[1;31m";
    private BufferedReader reader;
    private Writer writer;

    public CommandPrompt(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = writer;

        clear();
    }

    @Override
    public int getPlayerOption() {
        clear();
        askUserForPlayerChoice();
        //TODO inject into constructor
        TempInputValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        return asInteger(getValidInputForPlayerChoice(compoundValidator, input()));
    }

    @Override
    public int getNextMove(Board board) {
        print(board);
        askUserForTheirMove();
        TempInputValidator compoundValidator = new CompoundValidator(orderedListOfMoveValidators(board));

        String validInput = getValidMove(compoundValidator, input(), board);
        clear();
        return zeroIndexed(validInput);
    }

    @Override
    public String getReplayOption() {
        askUserToPlayAgain();

        TempInputValidator compoundValidator = new CompoundValidator(Collections.singletonList(new ReplayOptionValidator()));
        String replayOption = getValidReplayOption(compoundValidator, input());
        clear();
        return replayOption;
    }

    @Override
    public void print(Board board) {
        String boardForDisplay = BOARD_COLOUR_ANSII_CHARACTERS + newLine();

        Line[] rows = board.getRows();
        int offset = 0;
        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
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
                + colour(symbol)
                + FONT_COLOUR_ANSII_CHARACTERS
                + " has won");
    }

    @Override
    public void printDrawMessage() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "No winner this time");
    }

    private int asInteger(String input) {
        return Integer.valueOf(input);
    }

    private String getValidInputForPlayerChoice(TempInputValidator compoundValidator, String input) {
        ValidationResult validationResult = compoundValidator.isValid(input);
        while (!validationResult.isValid()) {
            display(validationResult.reason());
            askUserForPlayerChoice();
            validationResult = compoundValidator.isValid(input());
        }
        return validationResult.userInput();
    }

    private String getValidMove(TempInputValidator compoundValidator,
                                String input, Board currentBoard) {
        ValidationResult validationResult = compoundValidator.isValid(input);
        while (!validationResult.isValid()) {
            clear();
            print(currentBoard);
            display(validationResult.reason());
            validationResult = compoundValidator.isValid(input());
        }
        return validationResult.userInput();
    }

    private String getValidReplayOption(TempInputValidator compoundValidator,
                                        String input) {
        ValidationResult validationResult = compoundValidator.isValid(input);
        while (!validationResult.isValid()) {
            clear();
            display(validationResult.reason());
            validationResult = compoundValidator.isValid(input());
        }
        return validationResult.userInput();
    }

    private void askUserForPlayerChoice() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                        + "Choose opponent:"
                        + newLine()
                        + "Enter 1 to play Human vs Human"
        );
    }

    private void askUserForTheirMove() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Please enter the index for your next move");
    }

    private void display(String message) {
        try {
            writer.write(newLine() + message + newLine());
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private void clear() {
        display(CLEAR_SCREEN_ANSII_CHARACTERS);
    }

    private void askUserToPlayAgain() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Play again? [Y/N]");
    }

    public String input() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromPromptException(e.getMessage(), e);
        }
    }

    private List<InputValidator> orderedListOfMoveValidators(Board board) {
        return Arrays.asList(
                new NumericValidator(),
                new WithinGridBoundaryValidator(board),
                new FreeSpaceOnBoardValidator(board));
    }

    private int zeroIndexed(String input) {
        return Integer.valueOf(input) - 1;
    }

    private String displayCell(PlayerSymbol symbol, int cellOffset) {
        if (symbol == VACANT) {
            return colour(cellOffset);
        } else {
            return colour(symbol);
        }
    }

    private String colour(PlayerSymbol symbol) {

        if (symbol.equals(X)) {
            return X_COLOUR_ANSII_CHARACTERS + symbol.getSymbolForDisplay();
        }
        return O_COLOUR_ANSII_CHARACTERS + symbol.getSymbolForDisplay();
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
