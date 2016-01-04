package ttt.commandlineapp.inputvalidation;

import ttt.commandlineapp.InputValidator;
import ttt.commandlineapp.ValidationResult;
import ttt.game.Board;

public class WithinGridBoundaryValidator implements InputValidator {
    private final Board board;

    public WithinGridBoundaryValidator(Board board) {
        this.board = board;
    }

    @Override
    public ValidationResult isValid(String input) {
        if (board.isWithinGridBoundary(zeroIndexed(input))) {
            return new ValidationResult(input, true, "");
        }
        return new ValidationResult(input, false, invalidReason(input));
    }

    private int zeroIndexed(String input) {
        return Integer.valueOf(input) - 1;
    }

    private String invalidReason(String input) {
        return "[" + input + "] is outside of the grid boundary";
    }
}
