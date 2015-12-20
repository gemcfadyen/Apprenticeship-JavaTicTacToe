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
    }

    @Test
    public void initialisesGameAndPlayGame() {
        GameConfigurationSpy humanVsHumanGameConfiguration = new GameConfigurationSpy(HUMAN_VS_HUMAN);
        gameRulesSpy = new TicTacToeRulesSpy(new Board(3));
        GuiGameController controller = new GuiGameController(humanVsHumanGameConfiguration, gameRulesSpy, createViewFactory());
        controller.setGameType(HUMAN_VS_HUMAN);

        controller.presentBoard("3");

        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
    }

    @Test
    public void setsGameType() {
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(controller.getGameType(), is(HUMAN_VS_HUMAN));
    }

    @Test
    public void guiHumanGetsPreloadedWithMove() {
        GuiHumanPlayerSpy guiHumanPlayer = new GuiHumanPlayerSpy(X);
        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), guiHumanPlayer);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("1");

        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(guiHumanPlayer.hasBeenPreloadedWithMove(), is(true));
        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void gameHasWinner() {
        Board winningBoard = new Board(
                X, O, X,
                X, O, VACANT,
                X, VACANT, VACANT
        );
        gameRulesSpy = new TicTacToeRulesSpy(winningBoard);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("-1");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotWinnersSymbol(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
    }

    @Test
    public void gameHasDraw() {
        Board drawnBoard = new Board(
                X, O, X,
                O, O, X,
                X, X, O
        );
        gameRulesSpy = new TicTacToeRulesSpy(drawnBoard);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());

        controller.playMove("-1");

        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
        assertThat(gameRulesSpy.numberOfTimesBoardIsObtained(), is(1));
        assertThat(boardPresenterSpy.hasIdentifiedADraw(), is(true));
    }

    @Test
    public void winningOnLastTurnDisplaysWin() {
        Board winningBoard = new Board(
                X, O, X,
                X, O, O,
                X, X, O
        );
        gameRulesSpy = new TicTacToeRulesSpy(winningBoard);
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