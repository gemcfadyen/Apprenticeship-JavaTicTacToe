package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTypeTest {

    @Test
    public void translatesNumericRepresentationToGameType() {
        GameType gameType = GameType.of(1);
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }
}
