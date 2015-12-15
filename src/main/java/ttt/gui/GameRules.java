package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void initialiseGame(GameType gameType, String dimension);
    void takeTurn(int move);
    PlayerSymbol getWinningSymbol();
    boolean hasWinner();
    Board getBoard();
    boolean gameInProgress();
    boolean boardHasFreeSpace();
    int getCurrentPlayersNextMove();
}
