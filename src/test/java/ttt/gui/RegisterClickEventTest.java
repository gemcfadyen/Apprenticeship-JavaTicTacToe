package ttt.gui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegisterClickEventTest {

    @Test
    public void eventRegisteredWithElement() {
        ClickableElementSpy anyClickableElement = new ClickableElementSpy();
        ClickEvent anyClickEvent = new ClickEventStub();
        RegisterClickEvent registerClickEvent = new RegisterClickEvent();

        registerClickEvent.register(anyClickableElement, anyClickEvent);

        assertThat(anyClickableElement.hasClickEventRegistered(), is(true));
    }

    private static class ClickableElementSpy implements ClickableElement {
        private boolean clickActionSet = false;

        public void setClickAction(ClickEvent clickEvent) {
            clickActionSet = true;
        }

        public boolean hasClickEventRegistered() {
            return clickActionSet;
        }
    }

    private class ClickEventStub implements ClickEvent {
    }
}
