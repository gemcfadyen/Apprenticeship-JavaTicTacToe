package ttt.ui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
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

public class CommandPrompt implements Prompt {
    private static final String CLEAR_SCREEN_ANSII_CHARACTERS = "\033[H\033[2J";
    private BufferedReader reader;
    private Writer writer;
    private BoardFormatter boardFormatter;

    public CommandPrompt(Reader reader, Writer writer, BoardFormatter boardFormatter) {
        this.boardFormatter = boardFormatter;
        this.reader = new BufferedReader(reader);
        this.writer = writer;

        clear();
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
    public void presentGameTypes(List<GameType> gameTypes) {
        askUserForGameType(gameTypes);
    }

    @Override
    public void presentGridDimensionsUpTo(String largestDimension) {
        askUserForBoardDimension(Integer.valueOf(largestDimension));
    }

    @Override
    public void presentsBoard(Board board) {
        print(board);
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
        print(board);
        printWinningMessageFor(symbol);
    }

    @Override
    public void printsDrawMessage(Board board) {
        print(board);
        printDrawMessage();
    }

    private void print(Board board) {
        display(boardFormatter.formatForDisplay(board));
    }

    private void printWinningMessageFor(PlayerSymbol symbol) {
        display(boardFormatter.formatWinningMessage(symbol));
    }

    private void printDrawMessage() {
        display(boardFormatter.applyFontColour("No winner this time"));
    }

    private void clear() {
        display(CLEAR_SCREEN_ANSII_CHARACTERS);
    }

    private void askUserForBoardDimension(int largestDimension) {
        display(boardFormatter.formatBoardDimensionMessage(largestDimension));
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
        String gameTypeMessage = "";

        for (GameType gameType : gameTypes) {
            gameTypeMessage += "Enter " + gameType.numericRepresentation() + " to play " + gameType.gameNameForDisplay() + newLine();
        }

        display(boardFormatter.applyFontColour(gameTypeMessage));
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
            display(boardFormatter.applyInvalidColour(validationResult.reason()));
            askUserForBoardDimension(largestDimension);
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptGameType() {
        return validationResult -> {
            display(boardFormatter.applyInvalidColour(validationResult.reason()));
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
            display(boardFormatter.applyInvalidColour(validationResult.reason()));
            askUserToPlayAgain();
            return null;
        };
    }

    private void askUserToPlayAgain() {
        display(boardFormatter.applyFontColour("Play again? [Y/N]"));
    }

    private void askUserForTheirMove() {
        display(boardFormatter.applyFontColour("Please enter the index for your next move"));
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

    private String newLine() {
        return "\n";
    }

}
