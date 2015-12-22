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
    private DisplayFormatter displayFormatter;
    private final TextPresenter textPresenter;

    public CommandPrompt(Reader reader, Writer writer, DisplayFormatter displayFormatter, TextPresenter textPresenter) {
        this.displayFormatter = displayFormatter;
        this.reader = new BufferedReader(reader);
        this.writer = writer;
        this.textPresenter = textPresenter;

        clear();
    }

    @Override
    public int readBoardDimension(int lowerDimension, int largestDimension) {
        InputValidator compositeValidator = compositeFor(dimensionValidatorsFor(largestDimension));

        return asInteger(getValidInput(compositeValidator, input(), functionToRepromptForValidBoardDimension(lowerDimension, largestDimension)));
    }

    @Override
    public GameType readGameType(List<GameType> gameTypes) {
        InputValidator compositeValidator = compositeFor(gameTypeValidators());
        return GameType.of(asInteger(getValidInput(compositeValidator, input(), functionToRepromptGameType(gameTypes))));
    }

    @Override
    public ReplayOption readReplayOption() {
        InputValidator compoundValidator = compositeFor(Collections.singletonList(new ReplayOptionValidator()));
        return ReplayOption.of(getValidInput(compoundValidator, input(), functionToRepromptReplay()).toUpperCase());
    }

    @Override
    public int readNextMove(Board board) {
        print(board);
        askUserForTheirMove();

        String validInput = getValidInput(compositeFor(orderedListOfMoveValidators(board)), input(), functionToRepromptForValidMove(board));
        return zeroIndexed(validInput);
    }

    @Override
    public void presentGameTypes(List<GameType> gameTypes) {
        askUserForGameType(gameTypes);
    }

    @Override
    public void presentGridDimensionsBetween(int lowerBoundary, int upperBoundary) {
        display(textPresenter.chooseDimensionMessage(lowerBoundary, upperBoundary));
    }

    @Override
    public void presentsBoard(Board board) {
        print(board);
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
        print(board);
        display(textPresenter.winningMessage(symbol.getSymbolForDisplay()));
    }

    @Override
    public void printsDrawMessage(Board board) {
        print(board);
        printDrawMessage();
    }

    @Override
    public void presentReplayOption() {
        askUserToPlayAgain();
    }

    private void print(Board board) {
        display(displayFormatter.formatForDisplay(board));
    }

    private void printDrawMessage() {
        display(textPresenter.drawMessage());
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

    private void askUserForGameType(List<GameType> gameTypes) {
        display(textPresenter.chooseGameTypeMessage(gameTypes));
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

    private Function<ValidationResult, Void> functionToRepromptForValidBoardDimension(int lowerDimension, int largestDimension) {
        return validationResult -> {
            display(textPresenter.validationError(validationResult));
            presentGridDimensionsBetween(lowerDimension, largestDimension);
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptGameType(List<GameType> gameTypes) {
        return validationResult -> {
            display(textPresenter.validationError(validationResult));
            askUserForGameType(gameTypes);
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptForValidMove(Board currentBoard) {
        return validationResult -> {
            print(currentBoard);
            display(textPresenter.validationError(validationResult));
            askUserForTheirMove();
            return null;
        };
    }

    private Function<ValidationResult, Void> functionToRepromptReplay() {
        return validationResult -> {
            display(textPresenter.validationError(validationResult));
            askUserToPlayAgain();
            return null;
        };
    }

    private void askUserToPlayAgain() {
        display(textPresenter.replayMessage());
    }

    private void askUserForTheirMove() {
        display(textPresenter.chooseNextMoveMessage());
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
