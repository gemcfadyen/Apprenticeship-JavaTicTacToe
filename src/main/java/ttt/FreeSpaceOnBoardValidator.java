package ttt;

public class FreeSpaceOnBoardValidator implements InputValidator {
    private final Board board;

    public FreeSpaceOnBoardValidator(Board board) {
        this.board = board;
    }

    @Override
    public boolean isValid(String input) {
        return board.isVacantAt(Integer.valueOf(input) - 1);
    }

    @Override
    public String invalidReason(String input) {
        return "[" + input + "] is already occupied. Please re-enter a valid number within the grid boundary";
    }
}
