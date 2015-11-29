package ttt.gui;

import ttt.player.PlayerSymbol;

public class UserSelectsButtonForMove implements ClickEvent {
    private TemporaryGuiPrompt guiPrompt;
    private DeactivatableElement deactivableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(TemporaryGuiPrompt guiPrompt, DeactivatableElement deactivatableElement) {
        this.guiPrompt = guiPrompt;
        this.deactivableElement = deactivatableElement;
    }

    @Override
    public void action() {
        if (isActive) {
            PlayerSymbol symbol = guiPrompt.getCurrentPlayer();
            deactivableElement.setText(symbol.getSymbolForDisplay());
            String id = deactivableElement.getId();
            System.out.println("Player made move at " + id);
            guiPrompt.playMoveAt(id);
            deactivableElement.setDisabled();
            //board presenter check for win

            isActive = false;
        }
    }
}
