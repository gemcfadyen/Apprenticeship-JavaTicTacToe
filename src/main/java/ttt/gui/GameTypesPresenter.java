package ttt.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ttt.GameType;

import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class GameTypesPresenter {
    private final Scene scene;
    private final RegisterClickEvent registerClickEvent;
    private final GuiGameController controller;

    public GameTypesPresenter(Scene scene, RegisterClickEvent registerClickEvent, GuiGameController controller) {
        this.scene = scene;
        this.registerClickEvent = registerClickEvent;
        this.controller = controller;
    }

    private void setWelcomeMessage(GridPane gridPane) {
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 4, 1);
        gridPaneSetup(gridPane);
    }

    private void displayGameTypes(GridPane gridPane, List<GameType> gameTypes, RegisterClickEvent registerClickEvent, GuiGameController controller) {
        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSetupId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);

        int rowIndex = 4;
        for (GameType gameType : gameTypes) {
            RadioButton gameTypeSelectionButton = new RadioButton(gameType.gameNameForDisplay());
            gameTypeSelectionButton.setId(gameType.name());
            gridPane.add(gameTypeSelectionButton, 2, rowIndex++, 4, 1);
            registerActionForSelectingGameType(gameTypeSelectionButton, registerClickEvent, controller);
        }
    }

    private void registerActionForSelectingGameType(RadioButton radioButton, RegisterClickEvent registerClickEvent, GuiGameController controller) {
        ClickableElement gameSelectionButton = new JavaFxRadioButton(radioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(controller, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);
    }

    private void gridPaneSetup(GridPane gridPane) {
        gridPane.setAlignment(CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public void presentGameTypes(List<GameType> gameTypes) {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane, gameTypes, registerClickEvent, controller);

        scene.setRoot(gameTypePane);
    }
}
