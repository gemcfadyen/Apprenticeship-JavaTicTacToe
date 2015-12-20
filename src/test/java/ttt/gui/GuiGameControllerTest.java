package ttt.gui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.GuiHumanPlayer.IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;
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
        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY);
        GuiGameController controller = new GuiGameController(humanVsHumanGameConfiguration, gameRulesSpy, createViewFactory());
        controller.setGameType(HUMAN_VS_HUMAN);

        controller.presentBoard("3");

//        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));

//        assertThat(gameRulesSpy.numberOfTimesPlayerAskedForMove(), is(1));
//        assertThat(gameRulesSpy.hasMadeMove(), is(false));
        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
    }

//    @Test //TODO REMOVE!! Dupe dupe dupe
//    public void initialisesGameAndDisplaysBoardWithFirstAutomatedMove() {
//        GameConfigurationSpy unbeatableVsHumanGameConfiguration = new GameConfigurationSpy(UNBEATABLE_VS_HUMAN);
//        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), 1);
//        GuiGameController controller = new GuiGameController(unbeatableVsHumanGameConfiguration, gameRulesSpy, createViewFactory());
//        controller.setGameType(UNBEATABLE_VS_HUMAN);
//
//        controller.presentBoard("3");
//
//        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
//        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
//        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
////        assertThat(gameRulesSpy.getCurrentPlayersNextMove(), is(1));
////        assertThat(gameRulesSpy.hasMadeMove(), is(true));
////        assertThat(gameRulesSpy.gameInProgressCheck(), is(true));
//    }

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
        controller.setGameType(HUMAN_VS_HUMAN);

        controller.playMove("1");

        assertThat(gameRulesSpy.hasGotCurrentPlayer(), is(true));
        assertThat(guiHumanPlayer.hasBeenPreloadedWithMove(), is(true));
        assertThat(gameRulesSpy.hasGameBeenPlayed(), is(true));
//        assertThat(gameRulesSpy.gameInProgressCheck(), is(true));
//        assertThat(gameRulesSpy.getPositionOfMove(), is(1));
        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
//        assertThat(gameRulesSpy.numberOfTimesPlayerAskedForMove(), is(1));
//        assertThat(gameRulesSpy.numberOfMoves(), is(1));
    }

//    @Test
//    public void humanVsComputerGameMeansBothPlayersTakeTurn() {
//        GameConfigurationSpy humanVsUnbeatableGameConfiguration = new GameConfigurationSpy(HUMAN_VS_UNBEATABLE);
//        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), 1);
//        GuiGameController controller = new GuiGameController(humanVsUnbeatableGameConfiguration, gameRulesSpy, createViewFactory());
//        controller.setGameType(HUMAN_VS_UNBEATABLE);
//
//        controller.playMove("3");
//
//        assertThat(gameRulesSpy.hasMadeMove(), is(true));
//        assertThat(gameRulesSpy.numberOfMoves(), is(2));
//        assertThat(gameRulesSpy.numberOfTimesPlayerAskedForMove(), is(1));
//        assertThat(gameRulesSpy.gameInProgressCheck(), is(true));
//        assertThat(gameRulesSpy.numberOfTimesBoardCheckedForWin(), is(2));
//        assertThat(boardPresenterSpy.numberOfTimesBoardIsDrawn(), is(2));
//    }

//    @Test
//    public void unbeatablePlayerStopsWhenBoardIsFull() {
//        GameConfigurationSpy humanVsUnbeatableGameConfiguration = new GameConfigurationSpy(HUMAN_VS_UNBEATABLE);
//        gameRulesSpy = new TicTacToeRulesSpy(new Board(
//                VACANT, O, X,
//                X, O, X,
//                O, X, O), 0);
//        GuiGameController controller = new GuiGameController(humanVsUnbeatableGameConfiguration, gameRulesSpy, createViewFactory());
//        controller.setGameType(HUMAN_VS_UNBEATABLE);
//
//        controller.playMove("0");
//
//        assertThat(gameRulesSpy.hasMadeMove(), is(true));
//        assertThat(gameRulesSpy.numberOfMoves(), is(1));
//        assertThat(gameRulesSpy.numberOfTimesBoardCheckedForWin(), is(1));
//        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(false));
//        assertThat(boardPresenterSpy.numberOfTimesBoardIsDrawn(), is(1));
//    }
  //  @Test

//    public void unbeatablePlayerStopsWhenBoardHasWinner() {
//        GameConfigurationSpy humanVsUnbeatableGameConfiguration = new GameConfigurationSpy(HUMAN_VS_UNBEATABLE);
//        gameRulesSpy = new TicTacToeRulesSpy(new Board(
//                VACANT, VACANT, O,
//                X, O, O,
//                X, X, VACANT), 8);
//        GuiGameController controller = new GuiGameController(humanVsUnbeatableGameConfiguration, gameRulesSpy, createViewFactory());
//        controller.setGameType(HUMAN_VS_UNBEATABLE);
//
//        controller.playMove("8");
//
//        assertThat(gameRulesSpy.hasMadeMove(), is(true));
//        assertThat(gameRulesSpy.numberOfMoves(), is(1));
//        assertThat(gameRulesSpy.numberOfTimesBoardIsObtained(), is(1));
//        assertThat(gameRulesSpy.gameInProgressCheck(), is(true));
//        assertThat(gameRulesSpy.numberOfTimesBoardCheckedForWin(), is(1));
//        assertThat(boardPresenterSpy.hasIdentifiedAWin(), is(true));
//    }

//    @Test
//    public void playersTakeTurnsWhenGameTypeIsUnbeatableVsHuman() {
//        GameConfigurationSpy humanVsUnbeatableGameConfiguration = new GameConfigurationSpy(UNBEATABLE_VS_HUMAN);
//        gameRulesSpy = new TicTacToeRulesSpy(new Board(3), 1);
//        GuiGameController controller = new GuiGameController(humanVsUnbeatableGameConfiguration, gameRulesSpy, createViewFactory());
//        controller.setGameType(UNBEATABLE_VS_HUMAN);
//
//        controller.playMove("3");
//
//        assertThat(gameRulesSpy.hasMadeMove(), is(true));
//        assertThat(gameRulesSpy.numberOfTimesBoardIsObtained(), is(2));
//        assertThat(gameRulesSpy.numberOfMoves(), is(2));
//        assertThat(gameRulesSpy.numberOfTimesPlayerAskedForMove(), is(1));
//        assertThat(gameRulesSpy.boardCheckedForFreeSpace(), is(true));
//        assertThat(gameRulesSpy.gameInProgressCheck(), is(true));
//        assertThat(gameRulesSpy.numberOfTimesBoardCheckedForWin(), is(2));
//        assertThat(boardPresenterSpy.numberOfTimesBoardIsDrawn(), is(2));
//    }

    @Test
    public void gameHasWinner() {
        Board winningBoard = new Board(
                X, O, X,
                X, O, VACANT,
                X, VACANT, VACANT
        );
        gameRulesSpy = new TicTacToeRulesSpy(winningBoard, -1);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());
        controller.setGameType(HUMAN_VS_HUMAN);

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
        gameRulesSpy = new TicTacToeRulesSpy(drawnBoard, -1);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());
        controller.setGameType(HUMAN_VS_HUMAN);

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
        gameRulesSpy = new TicTacToeRulesSpy(winningBoard, 6);
        GuiGameController controller = new GuiGameController(gameConfigurationSpy, gameRulesSpy, createViewFactory());
        controller.setGameType(HUMAN_VS_HUMAN);

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