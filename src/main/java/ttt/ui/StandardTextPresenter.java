package ttt.ui;

import ttt.GameType;
import ttt.inputvalidation.ValidationResult;

import java.util.List;

public class StandardTextPresenter implements TextPresenter {

    @Override
    public String winningMessage(String winner) {
        return "Congratulations - " + winner + " has won";
    }

    @Override
    public String drawMessage() {
        return "No winner this time";
    }

    @Override
    public String validationError(ValidationResult validationResult) {
        return validationResult.reason();
    }

    @Override
    public String chooseGameTypeMessage(List<GameType> gameTypes) {
        String gameTypeMessage = "";

        for (GameType gameType : gameTypes) {
            gameTypeMessage += "Enter " + gameType.numericRepresentation() + " to play " + gameType.gameNameForDisplay() + newLine();
        }

        return gameTypeMessage;
    }

    @Override
    public String replayMessage() {
        return "Play again? [Y/N]";
    }

    @Override
    public String chooseNextMoveMessage() {
        return "Please enter the index for your next move";
    }

    @Override
    public String chooseDimensionMessage(int lowerBoundary, int upperBoundary) {
        return "Please enter the dimension of the board you would like to use [" + lowerBoundary + " to " + upperBoundary + "]";
    }

    private String newLine() {
        return "\n";
    }
}
