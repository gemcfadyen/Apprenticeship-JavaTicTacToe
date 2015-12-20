package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void initialiseGame(GameType gameType, String dimension);
    void takeTurn(int move);
    PlayerSymbol getWinningSymbol();
    boolean hasWinner();
    boolean noWinnerYet();
    Board getBoard();
    boolean gameInProgress();
    boolean boardHasFreeSpace();
    int getCurrentPlayersNextMove();

    void playGame();
}
