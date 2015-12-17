package ttt.gui;

import ttt.GameType;

import java.util.List;

public class PawelController {
    public void pawel(DisplayPresenter boardView, GameConfiguration gameConfiguration) {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        boardView.presentGameTypes(allGameTypes);
    }
}
