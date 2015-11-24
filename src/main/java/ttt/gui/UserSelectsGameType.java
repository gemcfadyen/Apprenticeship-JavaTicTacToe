package ttt.gui;

public class UserSelectsGameType implements ClickEvent {
    private ClickableElement gameSelectionRadioBox;
    private DimensionPrompt dimensionPrompt;
    private boolean isActive = true;

    public UserSelectsGameType(ClickableElement gameSelectionRadioBox, DimensionPrompt dimensionPrompt) {
        this.gameSelectionRadioBox = gameSelectionRadioBox;
        this.dimensionPrompt = dimensionPrompt;
    }

    @Override
    public void action() {
        if (isActive) {
            dimensionPrompt.promptForGameDimension();
            gameSelectionRadioBox.disable();
            isActive = false;
        }
    }
}
