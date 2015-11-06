package ttt;

public class NumericValidator implements InputValidator {
    @Override
    public boolean isValid(String input) {
        return isNumber(input);

    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] is not a valid number. Please re-enter a numeric value";
    }

    private boolean isNumber(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
