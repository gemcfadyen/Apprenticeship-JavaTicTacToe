package ttt.guiapp;

public class RegisterClickEvent {
    public RegisterClickEvent() {
    }

    public void register(ClickableElement clickableElement, ClickEvent clickEvent) {
        clickableElement.setClickAction(clickEvent);
    }
}
