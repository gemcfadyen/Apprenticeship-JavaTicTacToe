package ttt.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ttt.GameType;
import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.List;

public class TicTacToeBoardPresenter implements BoardPresenter {
    private RadioButton humanVsHumanRadioButton;
    private Scene scene;
    private TemporaryGuiPrompt guiPrompt;
    private RegisterClickEvent registerClickEvent;

    public TicTacToeBoardPresenter(Scene scene) {
        this.scene = scene;
        guiPrompt = new GuiPrompt(this);
        registerClickEvent = new RegisterClickEvent();
    }

    @Override
    public void presentGameTypes() {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);
        displayGameTypes(gameTypePane);
        ClickableElement gameSelectionButton = new JavaFxRadioButton(humanVsHumanRadioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(guiPrompt, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);
        scene.setRoot(gameTypePane);
    }

    @Override
    public void presentGridDimensionsFor(GameType gameType) {
        GridPane dimensionPane = new GridPane();
        setWelcomeMessage(dimensionPane);
        Text dimensionPrompt = new Text("Choose a board dimension");
        dimensionPrompt.setId("gameSetupId");
        RadioButton boardDimension = new RadioButton("3x3");
        boardDimension.setId("gameSetupSelectionId");
        dimensionPane.add(dimensionPrompt, 2, 2, 4, 1);
        dimensionPane.add(boardDimension, 2, 4, 4, 1);

        ClickableElement dimensionSelectionButton = new JavaFxRadioButton(boardDimension);
        ClickEvent boardDimensionOnClick = new UserSelectsBoardDimension(guiPrompt, dimensionSelectionButton);

        registerClickEvent.register(dimensionSelectionButton, boardDimensionOnClick);

        scene.setRoot(dimensionPane);
    }

    @Override
    public void presentsBoard(Board board) {
        GridPane boardPane = new GridPane();
        gridPaneSetup(boardPane);
        List<Line> rows = board.getRows();

        int displayRowIndex = 2;
        int displayColumnIndex = 2;
        int lineNumber = 0;
        int offset = 0;
        int dimension = rows.size();

        for (Line line : rows) {
            PlayerSymbol[] symbols = line.getSymbols();
            for (int i = 0; i < symbols.length; i++) {
                Button cell = createButton(String.valueOf(offset + i + 1), String.valueOf(i + offset));

                DeactivatableElement clickableCell = new JavaFxButton(cell);
                ClickEvent makeMoveOnClick = new UserSelectsButtonForMove(guiPrompt, clickableCell);
                registerClickEvent.register(clickableCell, makeMoveOnClick);

                HBox gridLayout = new HBox(10);
                gridLayout.getChildren().add(cell);
                boardPane.add(gridLayout, displayColumnIndex++, displayRowIndex);
            }
            offset += dimension + lineNumber;
            displayRowIndex++;
            displayColumnIndex = 2;
        }
        scene.setRoot(boardPane);

    }

    @Override
    public void printsWinning(Board board, PlayerSymbol symbol) {

    }

    @Override
    public void printsDraw(Board board) {

    }

    private Button createButton(String text, String value) {
        Button firstSquare = new Button(text);
        firstSquare.setId(value);
        firstSquare.setMinSize(100, 100);
        return firstSquare;
    }

    private void setWelcomeMessage(GridPane gridPane) {
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 6, 1);
        gridPaneSetup(gridPane);
    }

    private void gridPaneSetup(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
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
