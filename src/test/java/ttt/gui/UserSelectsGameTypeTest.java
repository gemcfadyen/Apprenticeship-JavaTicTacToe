package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsGameTypeTest {

    @Test
    public void promptsForDimensionWhenGameTypeIsSelected() {
        GameControllerSpy gameControllerSpy = new GameControllerSpy();
        ClickableElement gameSelectionRadioBox = new GameSelectionRadioBoxSpy();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(gameSelectionRadioBox, gameControllerSpy);

        userSelectsGameType.action();

        assertThat(gameControllerSpy.hasPresentedGridDimensions(), is(true));
    }

    private class GameSelectionRadioBoxSpy implements ClickableElement {

        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public void disable() {
        }
    }
}
