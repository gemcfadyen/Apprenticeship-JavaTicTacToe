package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsButtonForMoveTest {

    @Test
    public void userMakesAMove() {
        GuiGameControllerSpy controller = new GuiGameControllerSpy();
        DeactivableElementSpy button = new DeactivableElementSpy();
        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(controller, button);
        userSelectsButtonForMove.action();

        assertThat(controller.hasTakenMove(), is(true));
    }

    private static class DeactivableElementSpy implements DeactivatableElement {

        private DeactivableElementSpy() {
        }

        @Override
        public void setClickAction(ClickEvent clickEvent) {

        }

        @Override
        public String getText() {
            return "";
        }


        @Override
        public void setDisabled() {
        }

        @Override
        public String getId() {
            return "7";
        }

    }
}
