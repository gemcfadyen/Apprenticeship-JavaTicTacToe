package ttt.guiapp.eventlisteners;

import ttt.guiapp.RollOn;
import ttt.guiapp.RollableElement;

public class UserRollsMouseOnGameStatus implements RollOn {
    private RollableElement rolloverElement;
    private String text;

    public UserRollsMouseOnGameStatus(RollableElement rolloverElement, String text) {
        this.rolloverElement = rolloverElement;
        this.text = text;
    }

    @Override
    public void action() {
        rolloverElement.setText(text);
    }
}
