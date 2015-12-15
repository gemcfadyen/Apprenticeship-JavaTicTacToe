package ttt.gui;

public class RegisterRollEvent {

    public void register(RollableElement rollableElement, RollOn rollOn) {
        rollableElement.setOnMouseRollOver(rollOn);
    }

    public void register(RollableElement rollableElement, RollOff rollOff) {
        rollableElement.setOnMouseRollOff(rollOff);
    }

}
