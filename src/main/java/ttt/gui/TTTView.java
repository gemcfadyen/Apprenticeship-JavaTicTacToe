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
    private TicTacToeBoardController controller;
    private Scene scene;
    private RegisterClickEvent registerClickEvent;

    public TTTView(TicTacToeBoardController controller, Scene scene) {
        this.controller = controller;
        this.scene = scene;
        registerClickEvent = new RegisterClickEvent();
    }

    @Override
    public void presentGameTypes() {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane, "Human vs Human");

        ClickableElement gameSelectionButton = new JavaFxRadioButton(humanVsHumanRadioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(controller, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);

        scene.setRoot(gameTypePane);
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {

        GridPane dimensionPane = new GridPane();
        setWelcomeMessage(dimensionPane);

        Text dimensionPrompt = new Text("Choose a board dimension");
        dimensionPrompt.setId("gameSetupId");

        RadioButton boardDimension = new RadioButton("3x3");
        boardDimension.setId("gameSetupSelectionId");

        dimensionPane.add(dimensionPrompt, 2, 2, 4, 1);
        dimensionPane.add(boardDimension, 2, 4, 4, 1);

        ClickableElement dimensionSelectionButton = new JavaFxRadioButton(boardDimension);
        ClickEvent boardDimensionOnClick = new UserSelectsBoardDimension(null, controller, dimensionSelectionButton);

        registerClickEvent.register(dimensionSelectionButton, boardDimensionOnClick);

        scene.setRoot(dimensionPane);
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

    private void displayGameTypes(GridPane gridPane, String typeOfGame) {
        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSetupId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);

        humanVsHumanRadioButton = new RadioButton(typeOfGame);
        humanVsHumanRadioButton.setId("gameSetupSelectionId");
        gridPane.add(humanVsHumanRadioButton, 2, 4, 4, 1);
    }

}
