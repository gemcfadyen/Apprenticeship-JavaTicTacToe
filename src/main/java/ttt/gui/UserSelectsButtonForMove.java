package ttt.gui;

import ttt.player.PlayerSymbol;

public class UserSelectsButtonForMove implements ClickEvent {
    private GameRulesPrompt guiPrompt;
    private DeactivatableElement deactivableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(GameRulesPrompt guiPrompt, DeactivatableElement deactivatableElement) {
        this.guiPrompt = guiPrompt;
        this.deactivableElement = deactivatableElement;
    }

    @Override
    public void action() {
        if (isActive) {
            PlayerSymbol symbol = guiPrompt.getCurrentPlayer();
            deactivableElement.setText(symbol.getSymbolForDisplay());

            guiPrompt.playMoveAt(deactivableElement.getId());

            deactivableElement.setDisabled();
            isActive = false;
        }
    }
}
