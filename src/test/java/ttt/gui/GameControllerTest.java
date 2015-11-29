package ttt.gui;

import org.junit.Test;
import ttt.Game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameControllerTest {

    @Test
    public void displaysGameTypes() {
        GuiPromptSpy guiPrompt = new GuiPromptSpy();
        GameController gameController = new GameController(new Game(), guiPrompt);

        gameController.presentGameTypes();
        assertThat(guiPrompt.hasPresentedGameTypes(), is(true));
    }
}