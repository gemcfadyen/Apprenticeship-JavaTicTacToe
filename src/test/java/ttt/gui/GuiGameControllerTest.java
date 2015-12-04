package ttt.gui;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;

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

    @Test
    public void getsDimensionsForGametypeAndDisplaysToUser() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
        assertThat(gameRulesSpy.hasObtainedBoardDimensions(), is(true));
    }

    @Test
    public void initialisesGameAndDisplaysBoard() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.presentBoard("3");

        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
    }

    @Test
    public void setsGameType() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(gameRulesSpy.hasStoredGameType(), is(true));
    }


}