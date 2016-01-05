package ttt.guiapp.eventlisteners;

import org.junit.Before;
import org.junit.Test;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.DeactivatableElement;
import ttt.guiapp.GuiGameControllerSpy;
import ttt.guiapp.players.GuiHumanPlayer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.X;

public class UserSelectsButtonForMoveTest {

    private UserSelectsButtonForMove userSelectsButtonForMoveWithObserver;
    private GuiGameControllerSpy controller = new GuiGameControllerSpy();
    private DeactivableElementSpy button = new DeactivableElementSpy();
    private GuiHumanPlayer observingPlayer = new GuiHumanPlayer(X);

    @Before
    public void setup() {
        userSelectsButtonForMoveWithObserver = new UserSelectsButtonForMove(controller, button);
        userSelectsButtonForMoveWithObserver.register(observingPlayer);
    }

    @Test
    public void userMakesAMove() {
        userSelectsButtonForMoveWithObserver.action();

        assertThat(controller.hasTakenMove(), is(true));
    }

    @Test
    public void getsRegisteredObserver() {
        assertThat(userSelectsButtonForMoveWithObserver.getObserver(), is(observingPlayer));
    }

    @Test
    public void updateObserverOnAction() {
        userSelectsButtonForMoveWithObserver.action();

        assertThat(observingPlayer.isReady(), is(true));
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
