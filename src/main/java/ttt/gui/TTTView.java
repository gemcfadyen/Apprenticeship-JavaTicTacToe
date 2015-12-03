package ttt.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ttt.GameType;
import ttt.player.PlayerSymbol;
import ttt.ui.WritePromptForGui;

public class TTTView implements WritePromptForGui {
    private RadioButton humanVsHumanRadioButton;
    private Scene scene;
    private RegisterClickEvent registerClickEvent;

    public TTTView(Scene scene) {
        this.scene = scene;
        registerClickEvent = new RegisterClickEvent();
    }

    @Override
    public void presentGameTypes() {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane);

        ClickableElement gameSelectionButton = new JavaFxRadioButton(humanVsHumanRadioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(this, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);

        scene.setRoot(gameTypePane);
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {

    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void printBoard() {

    }

    @Override
    public void printDrawMessage() {

    }

    private void gridPaneSetup(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void setWelcomeMessage(GridPane gridPane) {
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 6, 1);
        gridPaneSetup(gridPane);
    }

    private void displayGameTypes(GridPane gridPane) {
        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSetupId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);
        humanVsHumanRadioButton = new RadioButton("Human vs Human");
        humanVsHumanRadioButton.setId("gameSetupSelectionId");
        gridPane.add(humanVsHumanRadioButton, 2, 4, 4, 1);
    }

}
