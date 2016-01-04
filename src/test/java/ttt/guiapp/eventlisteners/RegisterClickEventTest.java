package ttt.guiapp.eventlisteners;

import org.junit.Test;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.ClickableElement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegisterClickEventTest {

    @Test
    public void eventRegisteredWithElement() {
        ClickableElementSpy anyClickableElement = new ClickableElementSpy();
        RegisterClickEvent registerClickEvent = new RegisterClickEvent();

        registerClickEvent.register(anyClickableElement, new ClickEventStub());

        assertThat(anyClickableElement.hasClickEventRegistered(), is(true));
    }

    private static class ClickableElementSpy implements ClickableElement {
        private boolean clickActionSet = false;

        public void setClickAction(ClickEvent clickEvent) {
            clickActionSet = true;
        }

        @Override
        public String getText() {
            return "Text On Clickable Element";
        }

        public boolean hasClickEventRegistered() {
            return clickActionSet;
        }
    }

    private class ClickEventStub implements ClickEvent {
        @Override
        public void action() {

        }
    }
}
