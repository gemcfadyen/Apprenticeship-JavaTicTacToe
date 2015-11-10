package ttt.inputvalidation;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTypeValidatorTest {

    @Test
    public void invalidGameType() {
        GameTypeValidator validator = new GameTypeValidator();
        ValidationResult validationResult = validator.isValid("7");

        assertThat(validationResult.isValid(), is(false));
    }

    @Test
    public void validGameType() {
        GameTypeValidator validator = new GameTypeValidator();
        ValidationResult validationResult = validator.isValid("1");

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void invalidReason() {
        GameTypeValidator validator = new GameTypeValidator();
        ValidationResult validationResult = validator.isValid("7");

        assertThat(validationResult.reason(), is("[7] is not a valid game type"));
    }

}