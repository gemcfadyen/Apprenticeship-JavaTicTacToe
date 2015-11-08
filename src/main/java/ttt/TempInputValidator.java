package ttt;

public interface TempInputValidator {
    ValidationResult isValid(String input);

    String invalidReason(String input);
}
