package ttt.ui;

import ttt.GameType;
import ttt.inputvalidation.ValidationResult;

import java.util.List;

class TextFormatterSpy implements TextPresenter {
    private boolean hasPresentedGameDimensions = false;
    private int numberOfDimensionPrompts = 0;
    private int numberOfValidationErrors = 0;
    private int numberOfClearMessages = 0;
    private int numberOfMakeMoveMessages = 0;
    private int numberOfReplayMessages = 0;
    private int numberOfGameTypeMessages = 0;
    private int numberOfWinningMessages = 0;
    private int numberOfDrawMessages = 0;

    @Override
    public String winningMessage(String winner) {
        numberOfWinningMessages++;
        return "";
    }

    @Override
    public String drawMessage() {
        numberOfDrawMessages++;
        return "";
    }

    @Override
    public String validationError(ValidationResult validationResult) {
        numberOfValidationErrors++;
        return "";
    }

    @Override
    public String chooseGameTypeMessage(List<GameType> gameTypes) {
        numberOfGameTypeMessages++;
        return "";
    }

    @Override
    public String replayMessage() {
        numberOfReplayMessages++;
        return "";
    }

    @Override
    public String chooseNextMoveMessage() {
        numberOfMakeMoveMessages++;
        return "";
    }

    @Override
    public String chooseDimensionMessage(int lowerBoundary, int upperBoundary) {
        hasPresentedGameDimensions = true;
        numberOfDimensionPrompts++;
        return "";
    }

    @Override
    public String clearMessage() {
        numberOfClearMessages++;
        return "";
    }

    public boolean hasPresentedDimensions() {
        return hasPresentedGameDimensions;
    }

    public int numberOfBoardDimensionPrompts() {
        return numberOfDimensionPrompts;
    }

    public int numberOfValidationErrors() {
        return numberOfValidationErrors;
    }

    public int numberOfClearMessages() {
        return numberOfClearMessages;
    }

    public int numberOfTakeMoveMessage() {
        return numberOfMakeMoveMessages;
    }

    public int numberOfReplayMessages() {
        return numberOfReplayMessages;
    }

    public int numberOfGameTypesMessages() {
        return numberOfGameTypeMessages;
    }

    public int numberOfWinningMessages() {
        return numberOfWinningMessages;
    }

    public int numberOfDrawMessages() {
        return numberOfDrawMessages;
    }
}
