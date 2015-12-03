package ttt.gui;

import ttt.player.PlayerSymbol;

public interface GameInterface {

    void playMoveAt(String move);

    PlayerSymbol getCurrentPlayer();

    boolean hasWinner();

    boolean boardHasFreeSpace();

    void togglePlayer();
}
