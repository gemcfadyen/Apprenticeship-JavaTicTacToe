package ttt.inputvalidation;

import ttt.GameType;

public class GameTypeValidator implements InputValidator {
    @Override
    public ValidationResult isValid(String input) {
        for(GameType type : GameType.values()) {
            if(type.numericRepresentation() == Integer.valueOf(input)) {
                return new ValidationResult(input, true, "");
            }
        }
        return new ValidationResult(input, false, invalidReason(input));
    }

    private String invalidReason(String input) {
        return "[" + input + "] is not a valid game type";
    }
}
