package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void initialiseGame(GameType gameType, int dimension);
    void playGame();
    boolean hasWinner();
    boolean noWinnerYet();
    PlayerSymbol getWinningSymbol();
    Board getBoard();
    boolean hasAvailableMoves();
    Player getCurrentPlayer();
}
