package ttt.gui;

import ttt.player.GuiHumanPlayer;
import ttt.player.Player;

public class UserSelectsButtonForMove implements ClickEvent {
    private GameController controller;
    private DeactivatableElement deactivatableElement;
    private Player registeredPlayer;

    public UserSelectsButtonForMove(GameController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        registerObservers();
        controller.playMove(Integer.valueOf(deactivatableElement.getId()));
    }

    private void registerObservers() {
        ((GuiHumanPlayer) registeredPlayer).setMove(Integer.valueOf(deactivatableElement.getId()));
    }

    public void register(Player player) {
        registeredPlayer = player;
    }
}
