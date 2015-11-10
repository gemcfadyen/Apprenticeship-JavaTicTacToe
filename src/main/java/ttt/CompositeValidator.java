package ttt;

import java.util.List;

public class CompositeValidator implements InputValidator {
    private final List<InputValidator> inputValidators;

    public CompositeValidator(List<InputValidator> validators) {
        this.inputValidators = validators;
    }

    @Override
    public ValidationResult isValid(String input) {
        
        for (InputValidator inputValidator : inputValidators) {
            ValidationResult validationResult = inputValidator.isValid(input);
            if(!validationResult.isValid()) {
                return validationResult;
            }
        }
        return new ValidationResult(input, true, "");
    }
}
