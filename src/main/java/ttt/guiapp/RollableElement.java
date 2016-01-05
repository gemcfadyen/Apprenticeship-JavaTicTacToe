package ttt.guiapp;

public interface RollableElement {
    void setOnMouseRollOver(RollOn event);
    void setOnMouseRollOff(RollOff event);
    void setText(String text);
}
