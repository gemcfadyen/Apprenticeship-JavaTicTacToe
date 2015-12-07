package ttt.gui;

public interface ViewFactory {
    DisplayPresenter createView(GuiGameController gameController, GameRules gameRules);
}
