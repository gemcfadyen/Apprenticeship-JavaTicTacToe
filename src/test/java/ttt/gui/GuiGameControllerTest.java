package ttt.gui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

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

    @Test
    public void takesMove() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        Board board = new Board(3);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board);

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.playMove("1");

        assertThat(gameRulesSpy.hasMadeMove(), is(true));
        assertThat(gameRulesSpy.hasToggledPlayers(), is(true));
        assertThat(gameRulesSpy.getPositionOfMove(), is("1"));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void gameHasWinner() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();

        Board winningBoard = new Board(
                X, O, X,
                X, O, VACANT,
                X, VACANT, VACANT);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(winningBoard);

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.playMove("1");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
    }

    @Test
    public void gameHasDraw() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        Board drawnBoard = new Board(
                X, O, X,
                VACANT, O, X,
                X, VACANT, VACANT);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(drawnBoard);

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.playMove("7");

        assertThat(gameRulesSpy.boardCheckedForFreeSpace(), is(true));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void winningOnLastTurnDisplaysWin() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();

        Board winningBoard = new Board(
                X, O, X,
                X, O, O,
                X, X, O);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(winningBoard);

        ViewFactory viewFactoryStub = (gameController, gameRules) -> boardPresenterSpy;

        GuiGameController controller = new GuiGameController(gameRulesSpy, viewFactoryStub);
        controller.playMove("6");

        assertThat(gameRulesSpy.gameCheckedForWin(), is(true));
        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
        assertThat(boardPresenterSpy.getWinningSymbol(), is(X));
        assertThat(boardPresenterSpy.hasIdentifiedADraw(), is(false));
    }
}