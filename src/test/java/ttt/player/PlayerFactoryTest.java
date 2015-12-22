package ttt.player;

import org.junit.Test;
import ttt.ui.CommandPrompt;
import ttt.ui.PlainFormatter;
import ttt.ui.Prompt;
import ttt.ui.StandardTextPresenter;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.*;

public class PlayerFactoryTest {

    private PlainFormatter displayFormatter;
    private StandardTextPresenter standardTextPresenter;

    @Test
    public void createsHumanPlayers() {
        displayFormatter = new PlainFormatter();
        standardTextPresenter = new StandardTextPresenter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter(), displayFormatter, standardTextPresenter);
        PlayerFactory playerFactory = new CommandLinePlayerFactory(commandPrompt);

        Player[] player = playerFactory.createPlayers(HUMAN_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsHumanAndUnbeatablePlayerFor3x3() {
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter(), new PlainFormatter(), standardTextPresenter);
        PlayerFactory playerFactory = new CommandLinePlayerFactory(commandPrompt);

        Player[] player = playerFactory.createPlayers(HUMAN_VS_UNBEATABLE, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanPlayerFor3x3() {
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter(), new PlainFormatter(), standardTextPresenter);
        PlayerFactory playerFactory = new CommandLinePlayerFactory(commandPrompt);

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(UnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanFor4x4() {
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter(), new PlainFormatter(), standardTextPresenter);
        PlayerFactory playerFactory = new CommandLinePlayerFactory(commandPrompt);

        Player[] player = playerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 4);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(DelayedUnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }
}