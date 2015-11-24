package ttt.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFxGui extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 6, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSelectionPromptId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);
        RadioButton humanVsHumanRadioButton = new RadioButton("Human vs Human");
        humanVsHumanRadioButton.setId("gameTypeSelectionId");
        gridPane.add(humanVsHumanRadioButton, 2, 4, 4, 1);


        ClickableElement gameSelectionButton = new JavaFxRadioButton(humanVsHumanRadioButton);
        DimensionPrompt dimensionPrompt = new BoardDimensionPrompt();
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(gameSelectionButton, dimensionPrompt);
        RegisterClickEvent registerGameSelectionLogic = new RegisterClickEvent();
        registerGameSelectionLogic.register(gameSelectionButton, gameSelectionOnClick);

        //draw the page
        Scene scene = new Scene(gridPane, 700, 700);
        scene.getStylesheets().add(JavaFxGui.class.getResource("presentation.css").toExternalForm());
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
