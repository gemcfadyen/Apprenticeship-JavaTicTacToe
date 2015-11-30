package ttt.gui;

import ttt.player.PlayerSymbol;

public class UserSelectsButtonForMove implements ClickEvent {
    private GameRulesPrompt guiPrompt;
    private DeactivatableElement deactivatableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(GameRulesPrompt guiPrompt, DeactivatableElement deactivatableElement) {
        this.guiPrompt = guiPrompt;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        if (isActive) {
            PlayerSymbol symbol = guiPrompt.getCurrentPlayer();
            deactivatableElement.setText(symbol.getSymbolForDisplay());

            guiPrompt.playMoveAt(deactivatableElement.getId());

            deactivatableElement.setDisabled();
            isActive = false;
        }
    }
}
