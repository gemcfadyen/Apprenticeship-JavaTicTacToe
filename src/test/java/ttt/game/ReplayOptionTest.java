package ttt.game;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.ReplayOption.*;

public class ReplayOptionTest {

    @Test
    public void getsReplayOptionY() {
        ReplayOption option = of("Y");
        assertThat(option, is(Y));
    }

    @Test
    public void getsReplayOptionN() {
        ReplayOption option = of("N");
        assertThat(option, is(N));
    }
}
