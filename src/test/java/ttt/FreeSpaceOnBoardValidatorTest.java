package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.VACANT;
import static ttt.PlayerSymbol.X;

public class FreeSpaceOnBoardValidatorTest {

    @Test
    public void returnsFalseIfSpaceOnBoardIsOccupied() {
        Board board = new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        FreeSpaceOnBoardValidator freeSpaceOnBoardValidator = new FreeSpaceOnBoardValidator(board);

        ValidationResult validationResult = freeSpaceOnBoardValidator.isValid("1");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void returnsTrueIfSpaceOnBoardIsVacant() {
        Board board = new Board();
        FreeSpaceOnBoardValidator freeSpaceOnBoardValidator = new FreeSpaceOnBoardValidator(board);

        ValidationResult validationResult = freeSpaceOnBoardValidator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void informativeMessageWhenInvalid() {
        Board board = new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        FreeSpaceOnBoardValidator freeSpaceOnBoardValidator = new FreeSpaceOnBoardValidator(board);

        ValidationResult validationResult = freeSpaceOnBoardValidator.isValid("1");

        assertThat(validationResult.reason(), is("[1] is already occupied. Please re-enter a valid number within the grid boundary"));
    }
}