package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WithinGridBoundaryValidatorTest {

    @Test
    public void evaluatesFalseIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());
        assertThat(gridBoundaryValidator.isValid("100"), is(false));
    }

    @Test
    public void evaluatesTrueIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());
        assertThat(gridBoundaryValidator.isValid("1"), is(true));
    }

    @Test
    public void informativeMethodReturnedWhenInputIsValid() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());

        assertThat(gridBoundaryValidator.invalidReason("-1"), is("[-1] is outside of the grid boundary. Please re-enter a valid number within the grid boundary"));
    }


}