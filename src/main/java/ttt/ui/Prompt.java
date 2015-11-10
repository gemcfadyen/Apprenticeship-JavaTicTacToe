package ttt.ui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface Prompt {
    int getGameType();
    int getNextMove(Board board);
    String getReplayOption();

    void print(Board board);
    void printWinningMessageFor(PlayerSymbol symbol);
    void printDrawMessage();
}
