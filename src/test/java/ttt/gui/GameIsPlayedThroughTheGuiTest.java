package ttt.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;
import ttt.ui.Prompt;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameIsPlayedThroughTheGuiTest {
    private Scene scene;
    private GuiGameController controller;

    @BeforeClass
    public static void setUp() throws Exception {
        new JFXPanel();
    }

    @Before
    public void setup() {
        scene = new Scene(new GridPane(), 700, 700);
        Prompt unusedPrompt = new UnusedPrompt();
        TicTacToeRules ticTacToeRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(unusedPrompt));
        controller = new GuiGameController(
                new TicTacToeGameConfiguration(),
                ticTacToeRules,
                new JavaFxViewFactory(scene)
        );
    }

    @Test
    public void playersTakeTurnsUntilGameIsWon() {
        controller.presentGameTypes();

        selectHumanVsHumanGameType(scene);
        select3x3Grid(scene);

        playerXTakesMove(scene, "#0");
        playerOTakesMove(scene, "#1");
        playerXTakesMove(scene, "#3");
        playerOTakesMove(scene, "#4");
        playerXTakesMove(scene, "#6");

        assertThat(getWinningMessage(scene).getText(), is("Game Over - X won"));
    }

    @Test
    public void playersTakeTurnsUntilGameIsDrawn() {
        controller.presentGameTypes();

        selectHumanVsHumanGameType(scene);
        select3x3Grid(scene);

        playerXTakesMove(scene, "#0");
        playerOTakesMove(scene, "#1");
        playerXTakesMove(scene, "#2");
        playerOTakesMove(scene, "#4");
        playerXTakesMove(scene, "#3");
        playerOTakesMove(scene, "#5");
        playerXTakesMove(scene, "#7");
        playerOTakesMove(scene, "#6");
        playerXTakesMove(scene, "#8");

        assertThat(getWinningMessage(scene).getText(), is("Game Over - No winner"));
    }

    private void selectHumanVsHumanGameType(Scene scene) {
        pressRadioButton(scene, "#HUMAN_VS_HUMAN");
    }

    private void select3x3Grid(Scene scene) {
        pressRadioButton(scene, "#gameSetupSelectionId");
    }

    private Label getWinningMessage(Scene scene) {
        return (Label) scene.lookup("#gameOverTargetId");
    }

    private void playerXTakesMove(Scene scene, String selector) {
        pressButton(scene, selector);
    }

    private void playerOTakesMove(Scene scene, String selector) {
        pressButton(scene, selector);
    }

    private void pressRadioButton(Scene scene, String id) {
        RadioButton radioButton = (RadioButton) scene.lookup(id);
        radioButton.fire();
    }

    private void pressButton(Scene scene, String selector) {
        Button playersMove = (Button) scene.lookup(selector);
        playersMove.fire();
    }
}

