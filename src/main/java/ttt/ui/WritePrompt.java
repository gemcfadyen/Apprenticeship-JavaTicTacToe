package ttt.ui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface WritePrompt {
    void presentBoardDimensionsFor(GameType gameType);
    void printWinningMessageFor(PlayerSymbol symbol);
    void print(Board board);
    void printDrawMessage();
}
