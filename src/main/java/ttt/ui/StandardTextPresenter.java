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
    public String presentGameTypes(List<GameType> gameTypes) {
        String gameTypeMessage = "";

        for (GameType gameType : gameTypes) {
            gameTypeMessage += "Enter " + gameType.numericRepresentation() + " to play " + gameType.gameNameForDisplay() + newLine();
        }

        return gameTypeMessage;
    }

    private String newLine() {
        return "\n";
    }
}
