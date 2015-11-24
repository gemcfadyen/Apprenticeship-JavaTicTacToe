package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsGameTypeTest {

    @Test
    public void promptsForDimensionWhenGameTypeIsSelected() {
        DimensionPromptSpy dimensionPromptSpy = new DimensionPromptSpy();
        ClickableElement gameSelectionRadioBox = new GameSelectionRadioBoxSpy();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(gameSelectionRadioBox, dimensionPromptSpy);

        userSelectsGameType.action();

        assertThat(dimensionPromptSpy.hasPromptedForGridDimension(), is(true));
    }

    @Test
    public void disablesGameSelectionButtonWhenSelectionIsMade() {
        DimensionPromptSpy dimensionPromptSpy = new DimensionPromptSpy();
        GameSelectionRadioBoxSpy gameSelectionRadioBox = new GameSelectionRadioBoxSpy();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(gameSelectionRadioBox, dimensionPromptSpy);

        userSelectsGameType.action();

        assertThat(gameSelectionRadioBox.isDisabled(), is(true));
    }

    @Test
    public void clickingButtonTwiceOnlyAsksForGridDimensionsOnce() {
        DimensionPromptSpy dimensionPromptSpy = new DimensionPromptSpy();
        GameSelectionRadioBoxSpy gameSelectionRadioBox = new GameSelectionRadioBoxSpy();
        UserSelectsGameType userSelectsGameType = new UserSelectsGameType(gameSelectionRadioBox, dimensionPromptSpy);

        userSelectsGameType.action();
        userSelectsGameType.action();
        assertThat(dimensionPromptSpy.getNumberOfTimesPromptHasBeenCalled(), is(1));
    }

    private static class DimensionPromptSpy implements DimensionPrompt {
        private boolean promptedForGameDimension = false;
        private int numberOfTimesPromptHasBeenCalled = 0;

        @Override
        public void promptForGameDimension() {
            numberOfTimesPromptHasBeenCalled++;
            promptedForGameDimension = true;
        }

        public boolean hasPromptedForGridDimension() {
            return promptedForGameDimension;
        }

        public int getNumberOfTimesPromptHasBeenCalled() {
            return numberOfTimesPromptHasBeenCalled;
        }
    }

    private class GameSelectionRadioBoxSpy implements ClickableElement {
        private boolean isDisabled = false;

        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public void disable() {
            isDisabled = true;
        }

        public boolean isDisabled() {
            return isDisabled;
        }
    }
}
