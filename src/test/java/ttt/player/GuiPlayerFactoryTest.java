package ttt.player;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.*;

public class GuiPlayerFactoryTest {
    @Test
    public void createsGuiHumanPlayers() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();

        Player[] player = playerFactory.createPlayers(HUMAN_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(GuiHumanPlayer.class));
        assertThat(player[1], instanceOf(GuiHumanPlayer.class));
    }

    @Test
    public void createsGuiHumanAndUnbeatablePlayerFor3x3() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();

        Player[] player = playerFactory.createPlayers(HUMAN_VS_UNBEATABLE, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(GuiHumanPlayer.class));
        assertThat(player[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void createsUnbeatableAndGuiHumanPlayerFor3x3() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(UnbeatablePlayer.class));
        assertThat(player[1], instanceOf(GuiHumanPlayer.class));
    }

    @Test
    public void createsUnbeatableAndGuiHumanFor4x4() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 4);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(DelayedUnbeatablePlayer.class));
        assertThat(player[1], instanceOf(GuiHumanPlayer.class));
    }
}
