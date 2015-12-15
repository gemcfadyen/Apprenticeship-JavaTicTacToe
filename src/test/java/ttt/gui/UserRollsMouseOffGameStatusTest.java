package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRollsMouseOffGameStatusTest {
    private RollableElementSpy rollableElementSpy = new RollableElementSpy();

    @Test
    public void setsActionWhenMouseRollsOver() {
        UserRollsMouseOffGameStatus userRollsMouseOffGameStatus = new UserRollsMouseOffGameStatus(rollableElementSpy, "bye");

        userRollsMouseOffGameStatus.action();

        assertThat(rollableElementSpy.textHasBeenUpdated(), is(true));
    }

}