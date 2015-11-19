package ttt.inputvalidation;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WithinGivenRangeValidatorTest {

    @Test
    public void withinBoundary() {
        WithinGivenRangeValidator lessThanValue = new WithinGivenRangeValidator(10);

        ValidationResult validationResult = lessThanValue.isValid("1");
        assertThat(validationResult.isValid(), is(true));
        assertThat(validationResult.reason(), is(""));
    }

    @Test
    public void outsideOfLowerBoundary() {
        WithinGivenRangeValidator lessThanValue = new WithinGivenRangeValidator(10);

        ValidationResult validationResult = lessThanValue.isValid("-1");
        assertThat(validationResult.isValid(), is(false));
        assertThat(validationResult.reason(), is("[-1] is outside of the range 1 to 10"));
    }

    @Test
    public void largerThanUpperBoundary() {
        WithinGivenRangeValidator lessThanValue = new WithinGivenRangeValidator(10);

        ValidationResult validationResult = lessThanValue.isValid("100");
        assertThat(validationResult.isValid(), is(false));
        assertThat(validationResult.reason(), is("[100] is outside of the range 1 to 10"));

    }
}
