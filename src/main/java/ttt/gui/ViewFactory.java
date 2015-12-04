package ttt.gui;

public interface ViewFactory {
    BoardPresenter createView(GuiGameController gameController, GameRules gameRules);

}
