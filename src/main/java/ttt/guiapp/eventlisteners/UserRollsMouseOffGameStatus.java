package ttt.guiapp.eventlisteners;

import ttt.guiapp.RollOff;
import ttt.guiapp.RollableElement;

public class UserRollsMouseOffGameStatus implements RollOff {
    private RollableElement rollableElement;
    private String text;

    public UserRollsMouseOffGameStatus(RollableElement rollableElement, String text) {
        this.rollableElement = rollableElement;
        this.text = text;
    }

    @Override
    public void action() {
        rollableElement.setText(text);
    }
}
