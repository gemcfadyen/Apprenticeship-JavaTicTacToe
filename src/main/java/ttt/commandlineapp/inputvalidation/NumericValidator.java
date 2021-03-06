package ttt.commandlineapp.inputvalidation;

import ttt.commandlineapp.InputValidator;
import ttt.commandlineapp.ValidationResult;

public class NumericValidator implements InputValidator {
    @Override
    public ValidationResult isValid(String input) {
        return new ValidationResult(input, isNumber(input), invalidReason(input));

    }

    private boolean isNumber(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String invalidReason(String input) {
        return "[" + input + "] is not a valid integer";
    }
}
