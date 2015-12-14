package ttt.gui;

class RollableElementSpy implements RollableElement {

    private boolean hasActionOnMouseOver = false;
    private boolean hasActionOnMouseOff = false;
    private boolean textHasBeenUpdated = false;

    @Override
    public void setOnMouseRollOver(RollOn event) {
        hasActionOnMouseOver = true;
    }

    @Override
    public void setOnMouseRollOff(RollOff event) {
        hasActionOnMouseOff = true;
    }

    @Override
    public void setText(String text) {
        textHasBeenUpdated = true;
    }

    public boolean hasMouseRolledOnEvent() {
        return hasActionOnMouseOver;
    }

    public boolean hasMouseRolledOffEvent() {
        return hasActionOnMouseOff;
    }

    public boolean textHasBeenUpdated() {
        return textHasBeenUpdated;
    }
}

