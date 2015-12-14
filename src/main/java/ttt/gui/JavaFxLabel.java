package ttt.gui;

import javafx.scene.control.Label;

public class JavaFxLabel implements ClickableElement {
    private Label label;

    public JavaFxLabel(Label javaFxLabel) {
        this.label = javaFxLabel;
    }

    @Override
    public void setClickAction(ClickEvent clickEvent) {
        label.setOnMouseClicked(event -> clickEvent.action());
    }

    @Override
    public String getText() {
        return label.getText();
    }
}
