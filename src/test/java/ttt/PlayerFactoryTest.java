package ttt;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerFactoryTest {

    @Test
    public void createsHumanPlayer() {
        PlayerFactory playerFactory = new PlayerFactory();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), new StringWriter());

        Player[] player = playerFactory.createPlayers(1, commandPrompt);

        assertThat(player.length, is(2));
        assertThat(player[0], instanceOf(HumanPlayer.class));
        assertThat(player[1], instanceOf(HumanPlayer.class));
    }

}