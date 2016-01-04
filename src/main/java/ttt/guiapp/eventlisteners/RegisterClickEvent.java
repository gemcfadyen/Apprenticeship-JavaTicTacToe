package ttt.guiapp.eventlisteners;

import ttt.guiapp.ClickEvent;
import ttt.guiapp.ClickableElement;

public class RegisterClickEvent {
    public RegisterClickEvent() {
    }

    public void register(ClickableElement clickableElement, ClickEvent clickEvent) {
        clickableElement.setClickAction(clickEvent);
    }
}
