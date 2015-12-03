package ttt.gui;

import ttt.GameType;
import ttt.player.PlayerSymbol;

public interface TTTController {

//    void displayDimensionPanel(GameType gameType);

    void presentBoardDimensionsFor(GameType gameType);

    void printWinningMessageFor(PlayerSymbol symbol);

    void presentBoard(String dimensionForBoard);
}
