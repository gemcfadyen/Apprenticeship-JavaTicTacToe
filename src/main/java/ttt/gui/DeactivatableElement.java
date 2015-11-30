package ttt.gui;

public interface DeactivatableElement extends ClickableElement {
    void setDisabled();

    void setText(String text);

    String getId();
}
