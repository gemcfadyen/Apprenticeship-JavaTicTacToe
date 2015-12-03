package ttt.gui;

import ttt.player.PlayerSymbol;
import ttt.ui.WritePromptForGui;

public class UserSelectsButtonForMove implements ClickEvent {
    private WritePromptForGui prompt;
    private GameInterface gameRules;
    private DeactivatableElement deactivatableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(WritePromptForGui prompt, GameInterface gameRules, DeactivatableElement deactivatableElement) {
        this.prompt = prompt;
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
                prompt.printWinningMessageFor(symbol);
            } else if(!gameRules.boardHasFreeSpace()) {
                prompt.printDrawMessage();
            }
            gameRules.togglePlayer();

            deactivatableElement.setDisabled();
            isActive = false;
        }
    }
}
