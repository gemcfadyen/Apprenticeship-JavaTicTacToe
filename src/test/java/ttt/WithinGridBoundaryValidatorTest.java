package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WithinGridBoundaryValidatorTest {

    @Test
    public void evaluatesFalseIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());

        ValidationResult validationResult = gridBoundaryValidator.isValid("100");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void evaluatesTrueIfInputIsWithinGridBoundary() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());

        ValidationResult validationResult = gridBoundaryValidator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void informativeMethodReturnedWhenInputIsValid() {
        WithinGridBoundaryValidator gridBoundaryValidator = new WithinGridBoundaryValidator(new Board());

        ValidationResult validationResult = gridBoundaryValidator.isValid("-1");

        assertThat(validationResult.reason(), is("[-1] is outside of the grid boundary"));
    }
}