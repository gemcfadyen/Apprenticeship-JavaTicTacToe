package ttt.guiapp.javafx;

import javafx.scene.control.Button;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.DeactivatableElement;

public class JavaFxButton implements DeactivatableElement {
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

    @Override
    public void setDisabled() {
        button.setDisable(true);
    }

    @Override
    public String getId() {
        return button.getId();
    }
}
