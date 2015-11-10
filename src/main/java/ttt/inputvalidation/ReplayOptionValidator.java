package ttt.inputvalidation;

import ttt.ReplayOptions;

public class ReplayOptionValidator implements InputValidator {
    @Override
    public ValidationResult isValid(String input) {
        for (ReplayOptions replayOptions : ReplayOptions.values()) {
            if (replayOptions.name().equals(input)) {
                return new ValidationResult(input, true, "");
            }
        }
        return new ValidationResult(input, false, invalidReason(input));
    }

    private String invalidReason(String input) {
        return "[" + input + "] is not a valid replay option";
    }
}
