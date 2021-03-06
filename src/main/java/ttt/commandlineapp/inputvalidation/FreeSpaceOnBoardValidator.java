package ttt.commandlineapp.inputvalidation;

import ttt.commandlineapp.InputValidator;
import ttt.commandlineapp.ValidationResult;
import ttt.game.board.Board;

public class FreeSpaceOnBoardValidator implements InputValidator {
    private final Board board;

    public FreeSpaceOnBoardValidator(Board board) {
        this.board = board;
    }

    @Override
    public ValidationResult isValid(String input) {
        if (board.isVacantAt(zeroIndexed(input))) {
            return new ValidationResult(input, true, "");
        }
        return new ValidationResult(input, false, invalidReason(input));
    }

    private String invalidReason(String input) {
        return "[" + input + "] is already occupied";
    }

    private int zeroIndexed(String input) {
        return Integer.valueOf(input) - 1;
    }
}
