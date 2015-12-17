package ttt.gui;

import ttt.GameType;

import java.util.List;

public class GameTypeController {
    public void presentGameTypes(DisplayPresenter boardView, GameConfiguration gameConfiguration) {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        boardView.presentGameTypes(allGameTypes);
    }
}
