package ttt.guiapp.eventlisteners;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserRollsMouseOnGameStatusTest {

    private RollableElementSpy rollableElementSpy = new RollableElementSpy();

    @Test
    public void setsActionForWhenMouseRollsOff() {
        UserRollsMouseOnGameStatus userRollsMouseOnGameStatus = new UserRollsMouseOnGameStatus(rollableElementSpy, "hi");

        userRollsMouseOnGameStatus.action();

        assertThat(rollableElementSpy.textHasBeenUpdated(), is(true));
    }

}