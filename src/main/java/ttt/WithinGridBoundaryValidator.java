package ttt;

public class WithinGridBoundaryValidator implements InputValidator {
    private final Board board;

    public WithinGridBoundaryValidator(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValid(String input) {
        return board.isWithinGridBoundary(Integer.valueOf(input) - 1);
    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] is outside of the grid boundary. Please re-enter a valid number within the grid boundary";
    }
}
