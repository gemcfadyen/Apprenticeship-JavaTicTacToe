package ttt.guiapp;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsReplayTest {

    @Test
    public void replayRepresentsGameTypes() {
        GuiGameControllerSpy controller = new GuiGameControllerSpy();
        UserSelectsReplay userSelectsReplay = new UserSelectsReplay(controller);

        userSelectsReplay.action();

        assertThat(controller.hasPresentedGameTypes(), is(true));
    }
}
