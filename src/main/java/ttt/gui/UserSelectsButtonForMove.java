package ttt.gui;

public class UserSelectsButtonForMove implements ClickEvent {
    private GuiPrompt guiPrompt;
    private ClickableElement clickableElement;

    public UserSelectsButtonForMove(GuiPrompt guiPrompt, ClickableElement clickableElement) {
        this.guiPrompt = guiPrompt;
        this.clickableElement = clickableElement;
    }

    @Override
    public void action() {
        System.out.println("Selected cell: " + clickableElement.getText());
    }
}
