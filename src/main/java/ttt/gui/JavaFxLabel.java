package ttt.gui;

import javafx.scene.control.Label;

public class JavaFxLabel implements ClickableElement, RollableElement {
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

    @Override
    public void setOnMouseRollOver(RollOn rollEvent) {
        label.setOnMouseEntered(event -> rollEvent.action());
    }

    @Override
    public void setOnMouseRollOff(RollOff rollEvent) {
        label.setOnMouseExited(event -> rollEvent.action());
    }

    @Override
    public void setText(String text) {
        label.setText(text);
    }
}
