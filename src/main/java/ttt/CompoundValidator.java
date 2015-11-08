package ttt;

import java.util.Arrays;
import java.util.List;

public class CompoundValidator implements TempInputValidator {
    private final List<InputValidator> inputValidators;
    private String invalidReason = "";

    public CompoundValidator(List<InputValidator> validators) {
        this.inputValidators = validators;
    }

    @Override
    public ValidationResult isValid(String input) {
        for (InputValidator inputValidator : inputValidators) {
            if(!inputValidator.isValid(input)) {
                invalidReason = inputValidator.invalidReason(input);
                return new ValidationResult(input, false, invalidReason);
            }
        }
        return new ValidationResult(input, true, invalidReason);
    }

    @Override
    public String invalidReason(String input) {
        return invalidReason;
    }
}
