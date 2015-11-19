package ttt.inputvalidation;

public class WithinGivenRangeValidator implements InputValidator {
    private int lowerBoundary = 1;
    private int upperBoundary;

    public WithinGivenRangeValidator(int upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

    @Override
    public ValidationResult isValid(String input) {
        Integer numericInput = Integer.valueOf(input);
        if (numericInput <= upperBoundary
                && numericInput >= lowerBoundary) {
            return new ValidationResult(input, true, "");
        }
        return new ValidationResult(input, false, "[" + input + "] is outside of the range " + lowerBoundary + " to " + upperBoundary);
    }
}
