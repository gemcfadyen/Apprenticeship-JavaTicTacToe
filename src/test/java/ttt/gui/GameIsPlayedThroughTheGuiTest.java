package ttt.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GameIsPlayedThroughTheGuiTest {
    private Scene scene;

    @BeforeClass
    public static void setUp() throws Exception {
        new JFXPanel();
    }

    @Before
    public void setup() {
        scene = new Scene(new GridPane(), 700, 700);
    }

    @Test
    public void playersTakeTurnsUntilGameIsWon() throws Exception {
        TicTacToeBoardPresenter boardPresenter = new TicTacToeBoardPresenter(
                new GameRules(
                        new PlayerFactory(),
                        null,
                        new BoardFactory()
                ), scene);
        boardPresenter.presentGameTypes();

        selectHumanVsHumanGameType(scene);
        select3x3Grid(scene);

        playerXTakesMove(scene, "#0");
        playerOTakesMove(scene, "#1");
        playerXTakesMove(scene, "#3");
        playerOTakesMove(scene, "#4");
        playerXTakesMove(scene, "#6");

        assertThat(getWinningMessage(scene).getText(), is("Game Over, X won"));
    }

    private void selectHumanVsHumanGameType(Scene scene) {
        pressRadioButton(scene, "#gameSetupSelectionId");
    }

    private void select3x3Grid(Scene scene) {
        pressRadioButton(scene, "#gameSetupSelectionId");
    }

    private Text getWinningMessage(Scene scene) {
        return (Text) scene.lookup("#gameOverTargetId");
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

