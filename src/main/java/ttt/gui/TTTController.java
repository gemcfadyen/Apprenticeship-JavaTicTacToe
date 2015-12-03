package ttt.gui;

import ttt.GameType;
import ttt.player.PlayerSymbol;

public interface TTTController {

//    void displayDimensionPanel(GameType gameType);

    void printBoard();

    void presentBoardDimensionsFor(GameType gameType);

    void printWinningMessageFor(PlayerSymbol symbol);

}
