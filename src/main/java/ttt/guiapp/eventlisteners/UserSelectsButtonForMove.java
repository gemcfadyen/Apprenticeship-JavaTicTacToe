package ttt.guiapp.eventlisteners;

import ttt.game.GameController;
import ttt.game.Player;
import ttt.guiapp.ClickEvent;
import ttt.guiapp.DeactivatableElement;
import ttt.guiapp.MovePublisher;
import ttt.guiapp.players.MoveObserver;

public class UserSelectsButtonForMove implements ClickEvent, MovePublisher {
    private GameController controller;
    private DeactivatableElement deactivatableElement;
    private Player observer;

    public UserSelectsButtonForMove(GameController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
        register(controller.getCurrentPlayer());
    }

    @Override
    public void action() {
        int position = Integer.valueOf(deactivatableElement.getId());
        notifyObserver(position);
        controller.takeMove(position);
    }

    @Override
    public void notifyObserver(int indexOfMove) {
        ((MoveObserver)observer).update(indexOfMove);
    }

    @Override
    public void register(Player player) {
        this.observer = player;
    }

    public Player getObserver() {
        return observer;
    }
}
