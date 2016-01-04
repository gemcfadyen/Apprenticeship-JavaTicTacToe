package ttt.guiapp;

import org.junit.Test;
import ttt.game.GameConfiguration;
import ttt.game.GameType;
import ttt.game.TicTacToeGameConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static ttt.game.GameType.*;

public class GameConfigurationTest {
    @Test
    public void getGameTypes() {
        GameConfiguration gamesConfiguration = new TicTacToeGameConfiguration();

        List<GameType> gameTypes = gamesConfiguration.getGameTypes();

        assertThat(gameTypes, contains(HUMAN_VS_HUMAN, HUMAN_VS_UNBEATABLE, UNBEATABLE_VS_HUMAN));
    }

    @Test
    public void getDimensions() {
        GameConfiguration gamesConfiguration = new TicTacToeGameConfiguration();

        String dimension = gamesConfiguration.getDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is("5"));
    }
}
