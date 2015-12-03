package ttt.ui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface WritePromptForGuiNew {

    void presentGameTypes(String typeOfGame);

    void presentBoardDimensions(String gameType);

    void printWinningMessageFor(PlayerSymbol symbol, Board board);

    void printBoard(Board board);

    void printDrawMessage();

}
