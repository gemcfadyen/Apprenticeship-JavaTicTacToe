package ttt.ui;

import ttt.board.Board;
import ttt.board.Line;
import ttt.inputvalidation.*;
import ttt.player.PlayerSymbol;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static ttt.board.Board.BOARD_DIMENSION;
import static ttt.player.PlayerSymbol.*;
import static ttt.player.PlayerSymbol.VACANT;

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
    public int getGameType() {
        askUserForGameType();
        InputValidator compoundValidator = compositeFor(gameTypeValidators());
        return asInteger(getValidInput(compoundValidator, input(), functionToRepromptGameType()));
    }

    @Override
    public int getNextMove(Board board) {
        print(board);
        askUserForTheirMove();

        String validInput = getValidInput(compositeFor(orderedListOfMoveValidators(board)), input(), functionToRepromptForValidMove(board));
        return zeroIndexed(validInput);
    }

    @Override
    public String getReplayOption() {
        askUserToPlayAgain();
        InputValidator compoundValidator = compositeFor(Collections.singletonList(new ReplayOptionValidator()));
        return getValidInput(compoundValidator, input(), functionToRepromptReplay());
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

    private void clear() {
        display(CLEAR_SCREEN_ANSII_CHARACTERS);
    }

    private void display(String message) {
        try {
            writer.write(newLine() + message + newLine());
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private void askUserForGameType() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                        + "Enter 1 to play Human vs Human"
        );
    }

    private CompositeValidator compositeFor(List<InputValidator> validators) {
        return new CompositeValidator(validators);
    }

    private int asInteger(String input) {
        return Integer.valueOf(input);
    }

    private String getValidInput(InputValidator compoundValidator, String input, Function<ValidationResult, Void> reprompt) {
        ValidationResult validationResult = compoundValidator.isValid(input);
        while (!validationResult.isValid()) {
            clear();
            reprompt.apply(validationResult);
            validationResult = compoundValidator.isValid(input());
        }
        clear();
        return validationResult.userInput();
    }

    private Function<ValidationResult, Void> functionToRepromptGameType() {
        return validationResult -> {
            display(BOARD_COLOUR_ANSII_CHARACTERS + validationResult.reason());
            askUserForGameType();
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptForValidMove(Board currentBoard) {
        return validationResult -> {
            print(currentBoard);
            display(validationResult.reason());
            askUserForTheirMove();
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptReplay() {
        return validationResult -> {
            display(BOARD_COLOUR_ANSII_CHARACTERS + validationResult.reason());
            askUserToPlayAgain();
            return null;
        };
    }

    public String input() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromPromptException(e.getMessage(), e);
        }
    }

    private void askUserToPlayAgain() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Play again? [Y/N]");
    }

    private void askUserForTheirMove() {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Please enter the index for your next move");
    }

    private List<InputValidator> gameTypeValidators() {
        return Arrays.asList(new NumericValidator(), new GameTypeValidator());
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
