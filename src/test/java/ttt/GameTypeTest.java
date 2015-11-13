package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTypeTest {

    @Test
    public void getsHumanVsHumanGameType() {
        GameType gameType = GameType.of(1);
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }

    @Test
    public void getsHumanVsUnbeatableGameType() {
        GameType gameType = GameType.of(2);
        assertThat(gameType, is(GameType.HUMAN_VS_UNBEATABLE));
    }

    @Test
    public void getsUnbeatableVsHumanGameType() {
        GameType gameType = GameType.of(3);
        assertThat(gameType, is(GameType.UNBEATABLE_VS_HUMAN));
    }

    @Test
    public void getsUnbeatableVsUnbeatableGameType() {
        GameType gameType = GameType.of(4);
        assertThat(gameType, is(GameType.UNBEATABLE_VS_UNBEATABLE));
    }

    @Test
    public void defaultsToHumanVsHuman() {
        GameType gameType = GameType.of(100);
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }
}
