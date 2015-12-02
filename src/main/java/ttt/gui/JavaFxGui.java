package ttt.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFxGui extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new GridPane(), 700, 700);
        scene.getStylesheets().add(JavaFxGui.class.getResource("presentation.css").toExternalForm());
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

        TicTacToeBoardPresenter ticTacToeBoardPresenter = new TicTacToeBoardPresenter(scene);

        GuiPrompt guiPrompt = new GuiPrompt(ticTacToeBoardPresenter);
        guiPrompt.presentGameTypes();
    }
}
