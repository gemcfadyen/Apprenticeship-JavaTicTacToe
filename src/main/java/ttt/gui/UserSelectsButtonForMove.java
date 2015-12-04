package ttt.gui;

public class UserSelectsButtonForMove implements ClickEvent {
    private GameController controller;
    private DeactivatableElement deactivatableElement;
    private boolean isActive = true;

    public UserSelectsButtonForMove(GameController controller, DeactivatableElement deactivatableElement) {
        this.controller = controller;
        this.deactivatableElement = deactivatableElement;
    }

    @Override
    public void action() {
        if (isActive) {
//            PlayerSymbol symbol =  guiPrompt.getCurrentPlayer();
//            deactivatableElement.setText(symbol.getSymbolForDisplay());

//            guiPrompt.playMoveAt(deactivatableElement.getId());
            controller.playMove(deactivatableElement.getId());

            deactivatableElement.setDisabled();
            isActive = false;
        }
    }
}
