package ttt.ui;

import ttt.GameType;
import ttt.ReplayOption;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface Prompt {
    int getBoardDimension(GameType gameType);
    GameType getGameType();
    ReplayOption getReplayOption();
    int getNextMove(Board board);

    void print(Board board);
    void printWinningMessageFor(PlayerSymbol symbol);
    void printDrawMessage();
}
