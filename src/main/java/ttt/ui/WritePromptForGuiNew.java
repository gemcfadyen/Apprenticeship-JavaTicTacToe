package ttt.ui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface WritePromptForGuiNew {

    void presentGameTypes(String typeOfGame);

    void presentBoardDimensions(String gameType);

    void printWinningMessageFor(PlayerSymbol symbol);

    void printBoard(Board board);

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