package ttt.gui;

import ttt.player.GuiHumanPlayer;
import ttt.player.Player;

public class UserSelectsButtonForMove implements ClickEvent, MovePublisher {
    private GameController controller;
    private DeactivatableElement deactivatableElement;
    private Player observer;

    public UserSelectsButtonForMove(GameController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        int position = Integer.valueOf(deactivatableElement.getId());
        notifyObserver(position);
        controller.playMove(position);
    }

    @Override
    public void notifyObserver(int indexOfMove) {
        ((GuiHumanPlayer)observer).update(indexOfMove);
    }

    public void register(Player player) {
        this.observer = player;
    }

    public Player getObserver() {
        return observer;
    }
}
