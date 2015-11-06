package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumericValidatorTest {

    @Test
    public void returnsTrueWhenInputIsNumeric() {
        InputValidator validator = new NumericValidator();
        assertThat(validator.isValid("3"), is(true));
    }

    @Test
    public void returnsFalseWhenInputIsNotNumeric() {
        InputValidator validator = new NumericValidator();
        assertThat(validator.isValid("a"), is(false));
    }

    @Test
    public void reasonForNumberBeingInvalid() {
        InputValidator validator = new NumericValidator();
        assertThat(validator.invalidReason("a"), is("[a] is not a valid number. Please re-enter a numeric value"));
    }
}