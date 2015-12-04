package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsBoardDimensionTest {

    @Test
    public void presentsBoardWhenDimensionIsSelected() {
        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
        GuiGameControllerSpy controller = new GuiGameControllerSpy();
        ClickableElement dimensionSelectionButton = new ClickableElementStub();
        UserSelectsBoardDimension userSelectsBoardDimension = new UserSelectsBoardDimension(controller, dimensionSelectionButton);
        userSelectsBoardDimension.action();

        assertThat(controller.hasPresentedBoard(), is(true));
        assertThat(controller.boardSize(), is(3));
//        assertThat(guiPromptSpy.getDimension(), is(3));
    }

    private class ClickableElementStub implements ClickableElement {
        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public String getText() {
            return "3";
        }
    }
}
