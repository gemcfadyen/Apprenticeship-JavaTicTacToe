package ttt.inputvalidation;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WithinGridBoundaryValidatorTest {

    @Test
    public void evaluatesFalseIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board(3));

        ValidationResult validationResult = gridBoundaryValidator.isValid("100");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void evaluatesTrueIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board(3));

        ValidationResult validationResult = gridBoundaryValidator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void informativeMethodReturnedWhenInputIsValid() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board(3));

        ValidationResult validationResult = gridBoundaryValidator.isValid("-1");

        assertThat(validationResult.reason(), is("[-1] is outside of the grid boundary"));
    }
}