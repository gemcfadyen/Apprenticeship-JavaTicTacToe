package ttt.player;

import org.junit.Test;
import ttt.ui.CommandPrompt;
import ttt.ui.Prompt;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.*;

public class PlayerFactoryTest {
    @Test
    public void createsHumanPlayers() {
        PlayerFactory playerFactory = new PlayerFactory();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter());

        Player[] player = playerFactory.createPlayers(HUMAN_VS_HUMAN, commandPrompt, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsHumanAndUnbeatablePlayerFor3x3() {
        PlayerFactory playerFactory = new PlayerFactory();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter());

        Player[] player = playerFactory.createPlayers(HUMAN_VS_UNBEATABLE, commandPrompt, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanPlayerFor3x3() {
        PlayerFactory playerFactory = new PlayerFactory();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter());

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, commandPrompt, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(UnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanFor4x4() {
        PlayerFactory playerFactory = new PlayerFactory();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter());

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, commandPrompt, 4);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(DelayedUnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }
}