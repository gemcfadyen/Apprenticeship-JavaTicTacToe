package ttt.guiapp.eventlisteners;

import ttt.guiapp.RollOff;
import ttt.guiapp.RollOn;
import ttt.guiapp.RollableElement;

public class RegisterRollEvent {

    public void register(RollableElement rollableElement, RollOn rollOn) {
        rollableElement.setOnMouseRollOver(rollOn);
    }

    public void register(RollableElement rollableElement, RollOff rollOff) {
        rollableElement.setOnMouseRollOff(rollOff);
    }

}
