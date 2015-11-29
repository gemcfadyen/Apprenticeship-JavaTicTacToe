package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsBoardDimensionTest {

    @Test
    public void presentsBoardWhenDimensionIsSelected() {
        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
        ClickableElement dimensionSelectionButton = new ClickableElementStub();
        UserSelectsBoardDimension userSelectsBoardDimension = new UserSelectsBoardDimension(guiPromptSpy, dimensionSelectionButton);
        userSelectsBoardDimension.action();

        assertThat(guiPromptSpy.getNumberOfTimesBoardIsPrinted(), is(1));
        assertThat(guiPromptSpy.getDimension(), is(3));
    }

    private class ClickableElementStub implements ClickableElement {
        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public String getText() {
            return "3x3";
        }
    }
}
