package ttt.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;

public class JavaFxGui extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new GridPane(), 700, 700);
        scene.getStylesheets().add(JavaFxGui.class.getResource("/presentation.css").toExternalForm());
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();


        GameRules ticTacToeRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(new UnusedPrompt()));
        GuiGameController guiGameController = new GuiGameController(ticTacToeRules, new JavaFxViewFactory(scene));
        guiGameController.presentGameTypes();
    }
}
