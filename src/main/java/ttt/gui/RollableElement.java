package ttt.gui;

public interface RollableElement {
    void setOnMouseRollOver(RollOn event);

    void setOnMouseRollOff(RollOff event);

    void setText(String text);
}
