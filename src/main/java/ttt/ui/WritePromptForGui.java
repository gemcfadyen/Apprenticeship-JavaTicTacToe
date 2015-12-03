package ttt.ui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface WritePromptForGui {

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
