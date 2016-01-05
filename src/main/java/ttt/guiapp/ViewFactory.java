package ttt.guiapp;

import ttt.game.DisplayPresenter;
import ttt.game.GameRules;

public interface ViewFactory {
    DisplayPresenter createView(GuiGameController gameController, GameRules gameRules);
}
