package ttt;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CompoundValidatorTest {

    @Test
    public void inputSuccesfullyValidated() {
        CompoundValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compoundValidator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void inputNotValidated() {
        CompoundValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compoundValidator.isValid("A");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void reasonInputIsNotValid() {
        CompoundValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compoundValidator.isValid("A");

        assertThat(validationResult.reason(), is("[A] is not a valid number. Please re-enter a numeric value"));
    }

    @Test
    public void validatedInputHasNoReason() {
        CompoundValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compoundValidator.isValid("1");

        assertThat(validationResult.reason(), is(""));
    }

    @Test
    public void returnsUserInput() {
        CompoundValidator compoundValidator = new CompoundValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compoundValidator.isValid("1");

        assertThat(validationResult.userInput(), is("1"));
    }

    @Test
    public void inputNotValidatedIfOneOfSeveralValidationsFail() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompoundValidator compoundValidator = new CompoundValidator(validators);

        ValidationResult validationResult = compoundValidator.isValid("100");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void reasonForFailureReturnedWhenOneOfSeveralValidationsFails() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompoundValidator compoundValidator = new CompoundValidator(validators);

        ValidationResult validationResult = compoundValidator.isValid("100");

        assertThat(validationResult.reason(), is("[100] is outside of the grid boundary. Please re-enter a valid number within the grid boundary"));
    }

    @Test
    public void inputValidatedIfAllValidationsAreSuccessfull() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompoundValidator compoundValidator = new CompoundValidator(validators);

        ValidationResult validationResult = compoundValidator.isValid("7");

        assertThat(validationResult.isValid(), is(true));
    }
}