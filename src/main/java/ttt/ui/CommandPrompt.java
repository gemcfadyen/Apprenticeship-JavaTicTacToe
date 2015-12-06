package ttt.ui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.board.Line;
import ttt.inputvalidation.*;
import ttt.player.PlayerSymbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static ttt.player.PlayerSymbol.VACANT;
import static ttt.player.PlayerSymbol.X;

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

    //TODO remove
    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        askUserForBoardDimension(gameType.dimensionUpperBoundary());
    }

    @Override
    public int readBoardDimension(int largestDimension) {
        InputValidator compositeValidator = compositeFor(dimensionValidatorsFor(largestDimension));

        return asInteger(getValidInput(compositeValidator, input(), functionToRepromptForValidBoardDimension(largestDimension)));
    }

    //TODO read needs to take in the valid list of gametypes for validation
    @Override
    public GameType readGameType() {
        InputValidator compositeValidator = compositeFor(gameTypeValidators());
        return GameType.of(asInteger(getValidInput(compositeValidator, input(), functionToRepromptGameType())));
    }

    @Override
    public int getNextMove(Board board) {
        print(board);
        askUserForTheirMove();

        String validInput = getValidInput(compositeFor(orderedListOfMoveValidators(board)), input(), functionToRepromptForValidMove(board));
        return zeroIndexed(validInput);
    }

    @Override
    public ReplayOption getReplayOption() {
        askUserToPlayAgain();
        InputValidator compoundValidator = compositeFor(Collections.singletonList(new ReplayOptionValidator()));

        return ReplayOption.of(getValidInput(compoundValidator, input(), functionToRepromptReplay()));
    }

    @Override
    public void print(Board board) {
        String boardForDisplay = BOARD_COLOUR_ANSII_CHARACTERS + newLine();

        List<Line> rows = board.getRows();
        int dimension = rows.size();
        int offset = 0;
        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
                boardForDisplay +=
                        space()
                                + displayCell(symbol, offset)
                                + getBorderFor(offset, dimension);
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

    private void askUserForBoardDimension(int largestDimension) {
        display(FONT_COLOUR_ANSII_CHARACTERS
                + "Please enter the dimension of the board you would like to use [" + 1 + " to " + largestDimension + "]");
    }

    private void display(String message) {
        try {
            writer.write(newLine() + message + newLine());
            writer.flush();
        } catch (IOException e) {
            throw new WriteToPromptException("An exception occurred when writing", e);
        }
    }

    private void askUserForGameType(List<GameType> gameTypes) {
        String gameTypeMessage = FONT_COLOUR_ANSII_CHARACTERS;

        for (GameType gameType : gameTypes) {
            gameTypeMessage += "Enter " + gameType.numericRepresentation() + " to play " + gameType.gameNameForDisplay() + newLine();
        }

        display(gameTypeMessage);
    }

    private CompositeValidator compositeFor(List<InputValidator> validators) {
        return new CompositeValidator(validators);
    }

    private int asInteger(String input) {
        return Integer.valueOf(input);
    }

    public String input() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromPromptException(e.getMessage(), e);
        }
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

    private Function<ValidationResult, Void> functionToRepromptForValidBoardDimension(int largestDimension) {
        return validationResult -> {
            display(BOARD_COLOUR_ANSII_CHARACTERS + validationResult.reason());
            askUserForBoardDimension(largestDimension);
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptGameType() {
        return validationResult -> {
            display(BOARD_COLOUR_ANSII_CHARACTERS + validationResult.reason());
            askUserForGameType(Arrays.asList(GameType.values())); //TODO pass in from model
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

    private List<InputValidator> dimensionValidatorsFor(int largestDimesion) {
        return Arrays.asList(
                new NumericValidator(),
                new WithinGivenRangeValidator(largestDimesion)
        );
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
            return optionallyPad(cellOffset) + colour(cellOffset);
        } else {
            return space() + colour(symbol);
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

    private String getBorderFor(int position, int dimension) {
        String border;
        if (lastRow(position, dimension)) {
            border = space();
        } else if (endOfRow(position, dimension)) {
            border = dividingHorizontalLine(dimension);
        } else {
            border = space() + dividingVerticalLine();
        }
        return BOARD_COLOUR_ANSII_CHARACTERS + border;
    }

    private String optionallyPad(int position) {
        if (singleDigit(position)) {
            return space();
        }
        return "";
    }

    private String dividingVerticalLine() {
        return "|";
    }

    private String dividingHorizontalLine(int dimension) {
        String dividingLine = space() + newLine();
        for (int i = 0; i < dimension; i++) {
            dividingLine += "-----";
        }
        dividingLine += newLine();
        return dividingLine;
    }

    private String newLine() {
        return "\n";
    }

    private boolean singleDigit(int position) {
        return position < 9;
    }

    private String space() {
        return " ";
    }

    private boolean lastRow(int index, int dimension) {
        return index == (dimension * dimension - 1);
    }

    private boolean endOfRow(int index, int dimension) {
        return (index + 1) % dimension == 0;
    }

    @Override
    public void presentGameTypes(List<GameType> gameTypes) {
        askUserForGameType(gameTypes);
    }

    @Override
    public void presentGridDimensionsUpTo(String largestDimension) {
        askUserForBoardDimension(Integer.valueOf(largestDimension));
    }

    @Override
    public void presentsBoard(Board board) {

    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {

    }

    @Override
    public void printsDrawMessage(Board board) {

    }
}
