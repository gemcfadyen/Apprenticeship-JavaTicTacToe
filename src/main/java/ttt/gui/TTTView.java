package ttt.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;
import ttt.ui.WritePromptForGuiNew;

import java.util.List;
import java.util.function.Function;

import static ttt.player.PlayerSymbol.*;

public class TTTView implements WritePromptForGuiNew {
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
    public void presentGameTypes(String typeOfGame) {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane, typeOfGame);

        ClickableElement gameSelectionButton = new JavaFxRadioButton(humanVsHumanRadioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(controller, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);

        scene.setRoot(gameTypePane);
    }

    @Override
    public void presentBoardDimensions(String dimension) {

        GridPane dimensionPane = new GridPane();
        setWelcomeMessage(dimensionPane);

        Text dimensionPrompt = new Text("Choose a board dimension");
        dimensionPrompt.setId("gameSetupId");

        RadioButton boardDimension = new RadioButton(dimension);
        boardDimension.setId("gameSetupSelectionId");

        dimensionPane.add(dimensionPrompt, 2, 2, 4, 1);
        dimensionPane.add(boardDimension, 2, 4, 4, 1);

        ClickableElement dimensionSelectionButton = new JavaFxRadioButton(boardDimension);
        ClickEvent boardDimensionOnClick = new UserSelectsBoardDimension(controller, dimensionSelectionButton);

        registerClickEvent.register(dimensionSelectionButton, boardDimensionOnClick);

        scene.setRoot(dimensionPane);
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {

    }

    @Override
    public void printBoard(Board board) {
        GridPane boardPane = new GridPane();
        gridPaneSetup(boardPane);
        printBoardsOnPane(board, boardPane, getCellLabelForWinningBoard(board), registerEvent());

        scene.setRoot(boardPane);
    }

    @Override
    public void printDrawMessage() {

    }

    private Function getCellLabelForWinningBoard(Board board) {
        Function<Integer, String> getLabel = index -> {
            PlayerSymbol symbolAtIndex = board.getSymbolAt(index);

            return (symbolAtIndex == VACANT)
                    ? String.valueOf(index + 1)
                    : symbolAtIndex.getSymbolForDisplay();

        };

        return getLabel;
    }

    private Function<Button, Void> registerEvent() {
        return button -> {
            DeactivatableElement clickableCell = new JavaFxButton(button);
            ClickEvent makeMoveOnClick = new UserSelectsButtonForMove(controller, null, clickableCell);
            registerClickEvent.register(clickableCell, makeMoveOnClick);
            return null;
        };
    }

    private Function<Integer, String> getCellLabelForInitialBoard() {
        return index -> String.valueOf(index + 1);
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

    private void printBoardsOnPane(Board board, GridPane boardPane, Function<Integer, String> labelForCell, Function<Button, Void> actionOnCell) {
        List<Line> rows = board.getRows();

        int displayRowIndex = 2;
        int displayColumnIndex = 2;
        int offset = 0;
        int dimension = rows.size();

        for (Line line : rows) {
            PlayerSymbol[] symbols = line.getSymbols();
            for (int i = 0; i < symbols.length; i++) {
                Button cell = createButton(labelForCell.apply(i + offset), String.valueOf(i + offset));
                if (cell.getText() == X.name() || cell.getText() == O.name()) {
                    cell.setDisable(true);
                }
                actionOnCell.apply(cell);

                boardPane.add(configureHBox(cell), displayColumnIndex++, displayRowIndex);
            }
            offset += dimension;
            displayRowIndex++;
            displayColumnIndex = 2;
        }
    }

    private Button createButton(String text, String value) {
        Button cell = new Button(text);
        cell.setId(value);
        cell.setMinSize(100, 100);
        return cell;
    }

    private HBox configureHBox(Button cell) {
        HBox gridLayout = new HBox(10);
        gridLayout.setAlignment(Pos.CENTER);
        gridLayout.getChildren().add(cell);
        return gridLayout;
    }

}
