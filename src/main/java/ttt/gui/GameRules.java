package ttt.gui;

import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void playMoveAt(String move);
    PlayerSymbol getWinningSymbol();
    boolean hasWinner();
    void togglePlayer();
    Board getBoard();

    boolean boardHasFreeSpace();
    String getCurrentPlayersNextMove();
}
