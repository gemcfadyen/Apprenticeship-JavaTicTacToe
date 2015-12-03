package ttt.gui;

import ttt.player.PlayerSymbol;

public class UserSelectsButtonForMove implements ClickEvent {
    private TicTacToeBoardController controller;
    private GameInterface gameRules;
    private DeactivatableElement deactivatableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(TicTacToeBoardController controller, GameInterface gameRules, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.gameRules = gameRules;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        if (isActive) {
            PlayerSymbol symbol = gameRules.getCurrentPlayer();
            deactivatableElement.setText(symbol.getSymbolForDisplay());

            gameRules.playMoveAt(deactivatableElement.getId());

            if(gameRules.hasWinner()) {
                controller.printWinningMessageFor(symbol);
            } else if(!gameRules.boardHasFreeSpace()) {
                controller.printDrawMessage();
            }
            gameRules.togglePlayer();

            deactivatableElement.setDisabled();
            isActive = false;
        }
    }
}
