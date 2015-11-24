package ttt.gui;

import javafx.scene.control.RadioButton;

public class JavaFxRadioButton implements ClickableElement {
    private RadioButton radioButton;

    public JavaFxRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    @Override
    public void setClickAction(ClickEvent clickEvent) {
        radioButton.setOnAction(event -> clickEvent.action());
    }

    @Override
    public void disable() {
        radioButton.setDisable(true);
    }
}
