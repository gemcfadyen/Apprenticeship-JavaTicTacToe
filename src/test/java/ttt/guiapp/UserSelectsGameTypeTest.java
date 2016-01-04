package ttt.guiapp;

import org.junit.Test;
import ttt.game.GameType;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsGameTypeTest {

    private ClickableElementStub selectedRadioBox = new ClickableElementStub();
    private GuiGameControllerSpy controllerSpy = new GuiGameControllerSpy();

    @Test
    public void asksGameControllerForForDimensionWhenGameTypeIsSelected() {
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(
                controllerSpy,
                selectedRadioBox);

        userSelectsGameType.action();

        assertThat(controllerSpy.hasPresentedBoardDimensions(), is(true));
    }

    @Test
    public void capturesGameTypeChosen() {
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(
                controllerSpy,
                selectedRadioBox
        );

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
