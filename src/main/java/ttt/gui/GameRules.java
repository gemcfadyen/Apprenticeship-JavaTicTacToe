package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;

public interface GameRules {
    void initialiseGame(GameType gameType, int dimension);
    PlayerSymbol getWinningSymbol();
    boolean hasWinner();
    boolean noWinnerYet();
    Board getBoard();
    boolean hasAvailableMoves();
    void playGame();
    Player getCurrentPlayer();
}
