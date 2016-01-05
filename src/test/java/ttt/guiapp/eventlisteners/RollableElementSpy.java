package ttt.guiapp.eventlisteners;

import ttt.guiapp.RollOff;
import ttt.guiapp.RollOn;
import ttt.guiapp.RollableElement;

class RollableElementSpy implements RollableElement {
    private boolean textHasBeenUpdated = false;

    @Override
    public void setOnMouseRollOver(RollOn event) {
    }

    @Override
    public void setOnMouseRollOff(RollOff event) {
    }

    @Override
    public void setText(String text) {
        textHasBeenUpdated = true;
    }

    public boolean textHasBeenUpdated() {
        return textHasBeenUpdated;
    }
}

