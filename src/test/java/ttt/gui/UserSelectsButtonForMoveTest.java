package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsButtonForMoveTest {

//    @Test
//    public void buttonIsDisabledOnceSelected() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GuiGameControllerSpy controller = new GuiGameControllerSpy();
//        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(controller, guiPromptSpy, button);
//        userSelectsButtonForMove.action();
//
//        assertThat(controller.button.getDisabledStatus(), is(true));
//    }
//
//    @Test
//    public void buttonIsSetToDisabledOnce() {
//        GuiGameControllerSpy controller = new GuiGameControllerSpy();
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(controller, guiPromptSpy, button);
//        userSelectsButtonForMove.action();
//        userSelectsButtonForMove.action();
//        assertThat(button.numberOfTimesButtonIsDisabled(), is(1));
//    }


    @Test
    public void userMakesAMove() {
        GuiGameControllerSpy controller = new GuiGameControllerSpy();
        DeactivableElementSpy button = new DeactivableElementSpy();
        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(controller, button);
        userSelectsButtonForMove.action();

        assertThat(controller.hasTakenMove(), is(true));
//        assertThat(guiPromptSpy.moveTaken(), is(Integer.valueOf(button.getId())));
    }


//    @Test
//    public void boardUpdatedToReflectPlayersMove() {
//        GuiGameControllerSpy controller = new GuiGameControllerSpy();
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(controller, guiPromptSpy, button);
//        userSelectsButtonForMove.action();
//
//        assertThat(button.getLabel(), is(X.getSymbolForDisplay()));
//    }

    private static class DeactivableElementSpy implements DeactivatableElement {
        private boolean isDisabled = false;
        private int numberOfTimesButtonIsDisabled = 0;
        private String text;

        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public void setText(String text) {
           this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }


        @Override
        public void setDisabled() {
            numberOfTimesButtonIsDisabled++;
            isDisabled = true;
        }

        @Override
        public String getId() {
            return "7";
        }

        public boolean getDisabledStatus() {
            return isDisabled;
        }

        public int numberOfTimesButtonIsDisabled() {
            return numberOfTimesButtonIsDisabled;
        }

        public String getLabel() {
            return text;
        }
    }
}
