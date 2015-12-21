package ttt.gui;

import ttt.player.Player;

public interface MovePublisher {
    void notifyObserver(int indexOfMove);
    void register(Player player);
}
