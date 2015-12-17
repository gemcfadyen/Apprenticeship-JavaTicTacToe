package ttt.gui;

public interface ViewFactory {
    DisplayPresenter createView(GuiGameController gameController, GameTypeController gameTypeController, GameRules gameRules);
}
