package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void initialiseGame(GameType gameType, String dimension);
    void playMoveAt(String move);
    PlayerSymbol getCurrentPlayerSymbol();
    PlayerSymbol getWinningSymbol();
    boolean hasWinner();
    void togglePlayer();
    Board getBoard();

    boolean boardHasFreeSpace();
    String getCurrentPlayersNextMove();
}
