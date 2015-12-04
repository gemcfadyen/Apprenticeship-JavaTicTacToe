package ttt.gui;

import org.junit.Test;
import ttt.GameType;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsGameTypeTest {

    @Test
    public void asksGameControllerForForDimensionWhenGameTypeIsSelected() {
        GuiGameControllerSpy controllerSpy = new GuiGameControllerSpy();
        ClickableElement selectedRadioBox = new ClickableElementStub();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(controllerSpy, selectedRadioBox);

        userSelectsGameType.action();

        assertThat(controllerSpy.hasPresentedBoardDimensions(), is(true));
    }

    @Test
    public void capturesGameTypeChosen() {
        GuiGameControllerSpy controllerSpy = new GuiGameControllerSpy();
        ClickableElement selectedRadioBox = new ClickableElementStub();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(controllerSpy, selectedRadioBox);

        userSelectsGameType.action();

        assertThat(controllerSpy.capturedGameType(), is(GameType.HUMAN_VS_HUMAN));
    }

    private class ClickableElementStub implements ClickableElement {
        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public String getText() {
            return "Human vs Human";
        }
    }
}
