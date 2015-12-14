package ttt.gui;

import org.junit.Before;
import org.junit.Test;
import ttt.GameType;
import ttt.board.BoardFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ttt.GameType.*;

public class GameConfigurationTest {

    private GameConfiguration gameConfiguration;
    private PlayerFactorySpy playerFactory;

    @Before
    public void setup() {
        playerFactory = new PlayerFactorySpy();
        gameConfiguration = new TicTacToeGameConfiguration(new BoardFactory(), playerFactory);
    }

    @Test
    public void getGameTypes() {
        List<GameType> gameTypes = gameConfiguration.getGameTypes();

        assertThat(gameTypes, contains(HUMAN_VS_HUMAN, HUMAN_VS_UNBEATABLE, UNBEATABLE_VS_HUMAN));
    }

    @Test
    public void getDimensions() {
        String dimension = gameConfiguration.getDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is("5"));
    }

    @Test
    public void initialiseGame() {
        GameRules gameRules = gameConfiguration.initialiseGame(HUMAN_VS_HUMAN, 3);

        assertThat(gameRules, is(notNullValue()));
        assertThat(playerFactory.getGameTypeUsed(), is(HUMAN_VS_HUMAN));
    }
}
