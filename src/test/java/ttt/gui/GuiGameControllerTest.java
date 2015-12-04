package ttt.gui;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GuiGameControllerTest {
    @Test
    public void getsGameTypesFromGameAndDisplaysThemToUser() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.presentGameTypes();

        assertThat(boardPresenterSpy.hasPresentedGameTypes(), is(true));
        assertThat(gameRulesSpy.hasObtainedGameTypes(), is(true));
    }
}