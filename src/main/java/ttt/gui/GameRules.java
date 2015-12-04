package ttt.gui;

import ttt.GameType;
import ttt.player.PlayerSymbol;

public interface GameRules {

    void playMoveAt(String move);

    PlayerSymbol getCurrentPlayer();

    boolean hasWinner();

//    boolean boardHasFreeSpace();

    void togglePlayer();

//    void initialiseGame(int dimension);

    GameType getGameTypes();

    String getDimension(GameType gameType);
}
