package ttt.gui;

import org.junit.Test;
import ttt.GameType;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameControllerTest {

    @Test
    public void displaysPromptForGridDimension() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GameController gameController = new GameController(boardPresenterSpy);
        gameController.presentBoardDimensionsFor(GameType.HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
    }
}
