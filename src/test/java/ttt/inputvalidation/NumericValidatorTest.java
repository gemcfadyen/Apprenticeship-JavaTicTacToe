package ttt.inputvalidation;

import org.junit.Test;
import ttt.inputvalidation.InputValidator;
import ttt.inputvalidation.NumericValidator;
import ttt.inputvalidation.ValidationResult;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumericValidatorTest {

    @Test
    public void returnsTrueWhenInputIsNumeric() {
        InputValidator validator = new NumericValidator();

        ValidationResult validationResult = validator.isValid("3");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void returnsFalseWhenInputIsNotNumeric() {
        InputValidator validator = new NumericValidator();

        ValidationResult validationResult = validator.isValid("a");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void reasonForNumberBeingInvalid() {
        InputValidator validator = new NumericValidator();

        ValidationResult validationResult = validator.isValid("a");

        assertThat(validationResult.reason(), is("[a] is not a valid integer"));
    }
}