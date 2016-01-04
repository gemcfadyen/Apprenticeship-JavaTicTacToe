package ttt.guiapp;

import ttt.game.Player;

public interface MovePublisher {
    void notifyObserver(int indexOfMove);
    void register(Player player);
}
