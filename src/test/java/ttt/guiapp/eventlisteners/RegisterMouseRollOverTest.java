package ttt.guiapp.eventlisteners;

import org.junit.Test;
import ttt.guiapp.RollOff;
import ttt.guiapp.RollOn;
import ttt.guiapp.RollableElement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegisterMouseRollOverTest {
    private RollableElementSpy anyRollableElement = new RollableElementSpy();

    @Test
    public void eventRegisteredOnRollOff() {
        new RegisterRollEvent().register(anyRollableElement, new RollOffStub());

        assertThat(anyRollableElement.hasMouseRolledOffEvent(), is(true));
    }

    @Test
    public void eventRegisteredOnRollOver() {
        new RegisterRollEvent().register(anyRollableElement, new RollOnStub());

        assertThat(anyRollableElement.hasMouseRolledOnEvent(), is(true));
    }

    private static class RollOffStub implements RollOff {
        @Override
        public void action() {
        }
    }

    private static class RollOnStub implements RollOn {
        @Override
        public void action() {
        }
    }

    private static class RollableElementSpy implements RollableElement {

        private boolean hasActionOnMouseOver = false;
        private boolean hasActionOnMouseOff = false;

        @Override
        public void setOnMouseRollOver(RollOn event) {
            hasActionOnMouseOver = true;
        }

        @Override
        public void setOnMouseRollOff(RollOff event) {
            hasActionOnMouseOff = true;
        }

        @Override
        public void setText(String text) {
        }

        public boolean hasMouseRolledOnEvent() {
            return hasActionOnMouseOver;
        }

        public boolean hasMouseRolledOffEvent() {
            return hasActionOnMouseOff;
        }
    }
}
