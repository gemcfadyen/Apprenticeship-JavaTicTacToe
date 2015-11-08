package ttt;

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
        return "[" + input + "] is already occupied. Please re-enter a valid number within the grid boundary";
    }

    private int zeroIndexed(String input) {
        return Integer.valueOf(input) - 1;
    }
}
