package ttt.inputvalidation;

import org.junit.Test;
import ttt.board.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CompositeValidatorTest {

    @Test
    public void inputSuccessfullyValidated() {
        CompositeValidator compositeValidator = new CompositeValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compositeValidator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void inputNotValidated() {
        CompositeValidator compositeValidator = new CompositeValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compositeValidator.isValid("A");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void reasonInputIsNotValid() {
        CompositeValidator compositeValidator = new CompositeValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compositeValidator.isValid("A");

        assertThat(validationResult.reason(), is("[A] is not a valid integer"));
    }

    @Test
    public void validatedInputHasNoReason() {
        CompositeValidator compositeValidator = new CompositeValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compositeValidator.isValid("1");

        assertThat(validationResult.reason(), is(""));
    }

    @Test
    public void returnsUserInput() {
        CompositeValidator compositeValidator = new CompositeValidator(Collections.singletonList(new NumericValidator()));

        ValidationResult validationResult = compositeValidator.isValid("1");

        assertThat(validationResult.userInput(), is("1"));
    }

    @Test
    public void inputNotValidatedIfOneOfSeveralValidationsFail() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompositeValidator compositeValidator = new CompositeValidator(validators);

        ValidationResult validationResult = compositeValidator.isValid("100");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void reasonForFailureReturnedWhenOneOfSeveralValidationsFails() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompositeValidator compositeValidator = new CompositeValidator(validators);

        ValidationResult validationResult = compositeValidator.isValid("100");

        assertThat(validationResult.reason(), is("[100] is outside of the grid boundary"));
    }

    @Test
    public void inputValidatedIfAllValidationsAreSuccessfull() {
        List<InputValidator> validators = new ArrayList<>();
        validators.add(new NumericValidator());
        validators.add(new WithinGridBoundaryValidator(new Board()));
        CompositeValidator compositeValidator = new CompositeValidator(validators);

        ValidationResult validationResult = compositeValidator.isValid("7");

        assertThat(validationResult.isValid(), is(true));
    }
}