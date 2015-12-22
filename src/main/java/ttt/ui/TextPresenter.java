package ttt.ui;

import ttt.GameType;
import ttt.inputvalidation.ValidationResult;

import java.util.List;

public interface TextPresenter {
    String winningMessage(String winner);
    String drawMessage();
    String validationError(ValidationResult validationResult);
    String presentGameTypes(List<GameType> gameTypes);
    String replayMessage();
}
