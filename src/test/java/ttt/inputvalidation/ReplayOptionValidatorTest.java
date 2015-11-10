package ttt.inputvalidation;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReplayOptionValidatorTest {

    @Test
    public void replayOptionNReturnsTrue() {
        InputValidator validator = new ReplayOptionValidator();

        assertTrue(validator.isValid("N").isValid());
    }

    @Test
    public void replayOptionYReturnsTrue() {
        InputValidator validator = new ReplayOptionValidator();

        assertTrue(validator.isValid("Y").isValid());
    }

    @Test
    public void invalidOptionReturnsFalse() {
        InputValidator validator = new ReplayOptionValidator();

        assertFalse(validator.isValid("A").isValid());
    }

    @Test
    public void informativeMessage() {
        InputValidator validator = new ReplayOptionValidator();

        assertThat(validator.isValid("P").reason(), is("[P] is not a valid replay option"));
    }

}