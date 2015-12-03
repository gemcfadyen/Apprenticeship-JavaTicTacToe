package ttt.ui;

import ttt.GameType;
import ttt.player.PlayerSymbol;

public interface WritePromptForGui {

    void presentGameTypes();

    void presentBoardDimensionsFor(GameType gameType);

    void printWinningMessageFor(PlayerSymbol symbol);

    void printBoard();

    void printDrawMessage();

}
//    //read
//    int readBoardDimension(GameType gameType);
//
//    GameType readGameType();
//
//    ReplayOption getReplayOption();
//
//    int getNextMove(Board board);
//}
