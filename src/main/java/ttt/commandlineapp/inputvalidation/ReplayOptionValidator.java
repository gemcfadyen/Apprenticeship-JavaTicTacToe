package ttt.commandlineapp.inputvalidation;

import ttt.commandlineapp.InputValidator;
import ttt.commandlineapp.ValidationResult;
import ttt.game.ReplayOption;

public class ReplayOptionValidator implements InputValidator {
    @Override
    public ValidationResult isValid(String input) {
        for (ReplayOption replayOption : ReplayOption.values()) {
            if (replayOption.name().equalsIgnoreCase(input)) {
                return new ValidationResult(input, true, "");
            }
        }
        return new ValidationResult(input, false, invalidReason(input));
    }

    private String invalidReason(String input) {
        return "[" + input + "] is not a valid replay option";
    }
}
