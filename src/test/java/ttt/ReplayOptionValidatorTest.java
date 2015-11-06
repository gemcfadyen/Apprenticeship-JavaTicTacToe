package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReplayOptionValidatorTest {

    @Test
    public void replayOptionNReturnsTrue() {
        InputValidator validator = new ReplayOptionValidator();

        assertTrue(validator.isValid("N"));
    }

    @Test
    public void replayOptionYReturnsTrue() {
        InputValidator validator = new ReplayOptionValidator();

        assertTrue(validator.isValid("Y"));
    }

    @Test
    public void invalidOptionReturnsFalse() {
        InputValidator validator = new ReplayOptionValidator();

        assertFalse(validator.isValid("A"));
    }

    @Test
    public void informativeMessage() {
        InputValidator validator = new ReplayOptionValidator();

        assertThat(validator.invalidReason("P"), is("[P] is not a valid replay option. Please re-enter Y/N"));
    }

}