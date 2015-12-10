package ttt.gui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class GuiGameControllerTest {

    private DisplayPresenterSpy boardPresenterSpy = new DisplayPresenterSpy();
    private TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();
    private GameConfigurationSpy gameConfigurationSpy = new GameConfigurationSpy();

    @Test
    public void getsGameTypesFromGameAndDisplaysThemToUser() {
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.presentGameTypes();

        assertThat(boardPresenterSpy.hasPresentedGameTypes(), is(true));
        assertThat(gameConfigurationSpy.hasObtainedGameTypes(), is(true));
    }

    @Test
    public void getsDimensionsForGametypeAndDisplaysToUser() {
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
        assertThat(gameConfigurationSpy.hasObtainedBoardDimensions(), is(true));
    }

    @Test
    public void initialisesGameAndDisplaysBoard() {
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.presentBoard("3");

        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
    }

    @Test
    public void setsGameType() {
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(controller.getGameType(), is(HUMAN_VS_HUMAN));
    }

    @Test
    public void takesMove() {
        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), "1");
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("1");

        assertThat(gameRulesSpy.hasMadeMove(), is(true));
        assertThat(gameRulesSpy.hasToggledPlayers(), is(true));
        assertThat(gameRulesSpy.getPositionOfMove(), is("1"));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void gameHasWinner() {
        Board board = new Board(
                VACANT, O, X,
                X, O, VACANT,
                X, VACANT, VACANT
        );
        gameRulesSpy = new TicTacToeRulesSpy(board, "0");
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("0");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotWinnersSymbol(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
    }

    @Test
    public void gameHasDraw() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                X, VACANT, O
        );
        gameRulesSpy = new TicTacToeRulesSpy(board, "7");
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("7");

        assertThat(gameRulesSpy.boardCheckedForFreeSpace(), is(true));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void winningOnLastTurnDisplaysWin() {
        Board board = new Board(
                X, O, X,
                X, O, O,
                VACANT, X, O
        );
        gameRulesSpy = new TicTacToeRulesSpy(board, "6");
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("6");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotWinnersSymbol(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
        assertThat(boardPresenterSpy.hasIdentifiedADraw(), is(false));
    }

    private ViewFactory createViewFactory() {
        return (gameController, gameRules) -> boardPresenterSpy;
    }
}