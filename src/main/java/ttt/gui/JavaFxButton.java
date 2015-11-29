package ttt.gui;

import javafx.scene.control.Button;

public class JavaFxButton implements ClickableElement {
    private Button button;

    public JavaFxButton(Button button) {
        this.button = button;
    }

    @Override
    public void setClickAction(ClickEvent clickEvent) {
        button.setOnAction(event -> clickEvent.action());
    }

    @Override
    public String getText() {
        return button.getText();
    }
}
