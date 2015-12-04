package ttt.gui;

import org.junit.Test;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;
import ttt.ui.Prompt;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class GuiPromptTest {

    private static final Prompt UNUSED_PROMPT = null;

    @Test
    public void displaysPromptForGameType() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(new BoardFactory(), new PlayerFactory());
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy, gameRules);
        guiPrompt.presentGameTypes();

        assertThat(boardPresenterSpy.hasPresentedGameTypes(), is(true));
    }

    @Test
    public void displaysPromptForGridDimension() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(new BoardFactory(), new PlayerFactory());
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy, gameRules);
        guiPrompt.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
    }

    @Test
    public void displaysBoardOfSpecificSize() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(new BoardFactory(), new PlayerFactory());
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy, gameRules);
        Board board = new Board(3);
        guiPrompt.print(board);

        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }

    @Test
    public void playerMakesMove() {
        Board board = new Board(3);
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3));
        GuiPrompt guiPrompt = new GuiPrompt(new BoardPresenterSpy(), board, gameRules);

        guiPrompt.playMoveAt("7");

        assertThat(board.getSymbolAt(7), is(X));
        assertThat(guiPrompt.getCurrentPlayer(), is(O));
    }

    @Test
    public void identifiesWin() {
        Board board = new Board(
                VACANT, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);
        BoardPresenterSpy boardPresenter = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, null, 3));
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenter, board, gameRules);

        guiPrompt.playMoveAt("0");

        assertThat(boardPresenter.hasIdentifiedAWin(), is(true));
    }

    @Test
    public void identifiesDraw() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                X, VACANT, O);
        BoardPresenterSpy boardPresenter = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, null, 3));
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenter, board, gameRules);

        guiPrompt.playMoveAt("7");

        assertThat(boardPresenter.hasIdentifiedADraw(), is(true));
    }

    @Test
    public void whenLastMoveCreatesWinningRowThenWinningMessageReported() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, VACANT);
        BoardPresenterSpy boardPresenter = new BoardPresenterSpy();
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, null, 3));
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenter, board, gameRules);

        guiPrompt.playMoveAt("8");

        assertThat(boardPresenter.hasIdentifiedADraw(), is(false));
        assertThat(boardPresenter.hasIdentifiedAWin(), is(true));
    }
}
