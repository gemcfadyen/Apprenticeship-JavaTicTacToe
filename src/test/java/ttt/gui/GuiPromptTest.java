package ttt.gui;

import org.junit.Test;
import ttt.board.Board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_HUMAN;

public class GuiPromptTest {
    @Test
    public void displaysPromptForGameType() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy);
        guiPrompt.presentGameTypes();

        assertThat(boardPresenterSpy.hasPresentedGameTypes(), is(true));
    }

    @Test
    public void displaysPromptForGridDimension() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy);
        guiPrompt.presentBoardDimensionsFor(HUMAN_VS_HUMAN);

        assertThat(boardPresenterSpy.hasPresentedGridDimensions(), is(true));
    }

    @Test
    public void displaysBoardOfSpecificSize() {
        BoardPresenterSpy boardPresenterSpy = new BoardPresenterSpy();
        GuiPrompt guiPrompt = new GuiPrompt(boardPresenterSpy);
        Board board = new Board(3);
        guiPrompt.print(board);

        assertThat(boardPresenterSpy.hasDrawnBoard(), is(true));
    }
}
