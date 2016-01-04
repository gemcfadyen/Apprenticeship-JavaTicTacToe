package ttt.commandlineapp;

import org.junit.Test;
import ttt.commandlineapp.formatting.PlainBoard;
import ttt.commandlineapp.formatting.StandardTextPresenter;
import ttt.commandlineapp.players.CommandLinePlayerFactory;
import ttt.commandlineapp.players.HumanPlayer;
import ttt.game.Player;
import ttt.game.players.DelayedUnbeatablePlayer;
import ttt.game.players.UnbeatablePlayer;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.GameType.*;

public class PlayerFactoryTest {

    private CommandPrompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter(),
                                                            new PlainBoard(), new StandardTextPresenter()
    );
    private CommandLinePlayerFactory commandLinePlayerFactory = new CommandLinePlayerFactory(commandPrompt);

    @Test
    public void createsHumanPlayers() {
        Player[] player = commandLinePlayerFactory.createPlayers(HUMAN_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsHumanAndUnbeatablePlayerFor3x3() {
        Player[] player = commandLinePlayerFactory.createPlayers(HUMAN_VS_UNBEATABLE, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanPlayerFor3x3() {
        Player[] player = commandLinePlayerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 3);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(UnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void createsUnbeatableAndHumanFor4x4() {
        Player[] player = commandLinePlayerFactory.createPlayers(UNBEATABLE_VS_HUMAN, 4);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(DelayedUnbeatablePlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }
}