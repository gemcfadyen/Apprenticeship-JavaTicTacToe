package ttt.gui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class GuiGameControllerTest {

    private BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
    private TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();

    @Test
    public void getsGameTypesFromGameAndDisplaysThemToUser() {
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.presentGameTypes();

        assertThat(boardPresenterSpy.hasPresentedGameTypes(), is(true));
        assertThat(gameRulesSpy.hasObtainedGameTypes(), is(true));
    }

    @Test
    public void getsDimensionsForGametypeAndDisplaysToUser() {
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
        assertThat(gameRulesSpy.hasObtainedBoardDimensions(), is(true));
    }

    @Test
    public void initialisesGameAndDisplaysBoard() {
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.presentBoard("3");

        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
    }

    @Test
    public void setsGameType() {
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(gameRulesSpy.hasStoredGameType(), is(true));
    }

    @Test
    public void takesMove() {
        gameRulesSpy = new TicTacToeRulesSpy(new Board(3));
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.playMove("1");

        assertThat(gameRulesSpy.hasMadeMove(), is(true));
        assertThat(gameRulesSpy.hasToggledPlayers(), is(true));
        assertThat(gameRulesSpy.getPositionOfMove(), is("1"));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void gameHasWinner() {
        Board boardAfterWinningMoveTaken = new Board(
                X, O, X,
                X, O, VACANT,
                X, VACANT, VACANT
        );
        gameRulesSpy = new TicTacToeRulesSpy(boardAfterWinningMoveTaken);
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.playMove("1");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
    }

    @Test
    public void gameHasDraw() {
        Board boardAfterMoveTaken = new Board(
                X, O, X,
                VACANT, O, X,
                X, X, VACANT
        );
        gameRulesSpy = new TicTacToeRulesSpy(boardAfterMoveTaken);
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.playMove("7");

        assertThat(gameRulesSpy.boardCheckedForFreeSpace(), is(true));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void winningOnLastTurnDisplaysWin() {
        Board boardAfterLastMoveTaken = new Board(
                X, O, X,
                X, O, O,
                X, X, O
        );
        gameRulesSpy = new TicTacToeRulesSpy(boardAfterLastMoveTaken);
        GuiGameController controller = new GuiGameController(gameRulesSpy, createViewFactory());

        controller.playMove("6");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
        assertThat(boardPresenterSpy.hasIdentifiedADraw(), is(false));
    }

    private ViewFactory createViewFactory() {
        return (gameController, gameRules) -> boardPresenterSpy;
    }
}