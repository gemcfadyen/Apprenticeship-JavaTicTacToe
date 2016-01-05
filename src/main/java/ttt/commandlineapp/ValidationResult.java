package ttt.commandlineapp;

public class ValidationResult {
    private final boolean isValid;
    private final String invalidReason;
    private final String input;

    public ValidationResult(String input, boolean isValid, String invalidReason) {
        this.input = input;
        this.isValid = isValid;
        this.invalidReason = invalidReason;
    }

    public boolean isValid() {
        return isValid;
    }

    public String reason() {
        return invalidReason;
    }

    public String userInput() {
        return input;
    }
}
