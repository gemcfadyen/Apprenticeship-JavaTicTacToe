package ttt.guiapp.javafx;

import javafx.scene.control.RadioButton;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.ClickableElement;

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
    public String getText() {
        return radioButton.getText();
    }

}
