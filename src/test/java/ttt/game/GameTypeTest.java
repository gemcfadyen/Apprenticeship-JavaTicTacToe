package ttt.game;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameTypeTest {

    @Test
    public void getsHumanVsHumanGameTypeFromDisplayName() {
        GameType gameType = GameType.of("Human vs Human");
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }

    @Test
    public void getHumanVsUnbeatableTypeFromDisplayName() {
        GameType gameType = GameType.of("Human vs Unbeatable");
        assertThat(gameType, is(GameType.HUMAN_VS_UNBEATABLE));
    }

    @Test
    public void getUnbeatableVsHumanTypeFromDisplayName() {
        GameType gameType = GameType.of("Unbeatable vs Human");
        assertThat(gameType, is(GameType.UNBEATABLE_VS_HUMAN));
    }

    @Test
    public void getDefaultGameTypeFromDisplayName() {
        GameType gameType = GameType.of("Another");
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }

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
    public void defaultsToHumanVsHuman() {
        GameType gameType = GameType.of(100);
        assertThat(gameType, is(GameType.HUMAN_VS_HUMAN));
    }

    @Test
    public void getPossibleDimensionForHumanVsHuman() {
        GameType gameType = GameType.of(1);
        assertThat(gameType.dimensionUpperBoundary(), is(5));
    }

    @Test
    public void getPossibleDimensionForHumanVsUnbeatable() {
        GameType gameType = GameType.of(2);
        assertThat(gameType.dimensionUpperBoundary(), is(4));
    }

    @Test
    public void getPossibleDimensionForUnbeatableVsHuman() {
        GameType gameType = GameType.of(3);
        assertThat(gameType.dimensionUpperBoundary(), is(4));
    }

    @Test
    public void getsLowerBoundaryForDimension() {
        GameType gameType = GameType.of(3);
        assertThat(gameType.dimensionLowerBoundary(), is(2));
    }
}
