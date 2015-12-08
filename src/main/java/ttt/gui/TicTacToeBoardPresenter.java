package ttt.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ttt.GameType;
import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static javafx.geometry.Pos.CENTER;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class TicTacToeBoardPresenter implements DisplayPresenter {
    private Scene scene;
    private RegisterClickEvent registerClickEvent;
    private GuiGameController controller;

    public TicTacToeBoardPresenter(GuiGameController controller, Scene scene) {
        this.scene = scene;
        this.controller = controller;
        this.registerClickEvent = new RegisterClickEvent();
    }

    @Override
    public void presentGameTypes(List<GameType> gameTypes) {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane, gameTypes);

        scene.setRoot(gameTypePane);
    }

    @Override
    public void presentGridDimensionsUpTo(String upperBoundaryOfPossibleDimension) {
        GridPane dimensionPane = new GridPane();
        setWelcomeMessage(dimensionPane);
        //TODO this will be updated to use the dimension passed in once the story to deal with multiple dimensions is taken on
        displayDimensions("3", dimensionPane);

        scene.setRoot(dimensionPane);
    }

    private void displayDimensions(String dimension, GridPane dimensionPane) {
        Text dimensionPrompt = new Text("Choose a board dimension");
        dimensionPrompt.setId("gameSetupId");

        RadioButton boardDimension = new RadioButton(dimension);
        boardDimension.setId("gameSetupSelectionId");

        dimensionPane.add(dimensionPrompt, 2, 2, 4, 1);
        dimensionPane.add(boardDimension, 2, 4, 4, 1);

        registerActionForSelectingDimension(boardDimension);
    }

    @Override
    public void presentsBoard(Board board) {
        GridPane boardPane = new GridPane();
        gridPaneSetup(boardPane);
        unusedTextBoxForConsistantLayout(boardPane);

        gridPaneSetup(boardPane);
        printBoardsOnPane(board, boardPane, getCellLabelForActiveBoard(board), registerEvent());


        scene.setRoot(boardPane);
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        winningStatus(symbol, gameOverPane);
        printBoardsOnPane(board, gameOverPane, getCellLabelForActiveBoard(board), disable());

        scene.setRoot(gameOverPane);
    }

    @Override
    public void printsDrawMessage(Board board) {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        drawStatus(gameOverPane);
        printBoardsOnPane(board, gameOverPane, getCellLabelForDrawnBoard(board), disable());

        scene.setRoot(gameOverPane);
    }

    @Override
    public void presentReplayOption() {
    }

    private void setWelcomeMessage(GridPane gridPane) {
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 4, 1);
        gridPaneSetup(gridPane);
    }

    private void displayGameTypes(GridPane gridPane, List<GameType> gameType) {
        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSetupId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);
        //TODO the list input will be used here when multiple gametypes handled
        RadioButton humanVsHumanRadioButton = new RadioButton(HUMAN_VS_HUMAN.gameNameForDisplay());
        humanVsHumanRadioButton.setId("gameSetupSelectionId");
        gridPane.add(humanVsHumanRadioButton, 2, 4, 4, 1);

        registerActionForSelectingGameType(humanVsHumanRadioButton);
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
                actionOnCell.apply(cell);

                boardPane.add(configureHBox(cell), displayColumnIndex++, displayRowIndex);
            }
            offset += dimension;
            displayRowIndex++;
            displayColumnIndex = 2;
        }
    }

    private void gridPaneSetup(GridPane gridPane) {
        gridPane.setAlignment(CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void winningStatus(PlayerSymbol symbol, GridPane gameOverPane) {
        Label gameOverTarget = new Label("Game Over - " + symbol.getSymbolForDisplay() + " won");
        gameOverTarget.setId("gameOverTargetId");
        positionLabelUnderBoard(gameOverPane, gameOverTarget);
    }

    private void drawStatus(GridPane gameOverPane) {
        Label gameOverTarget = new Label("Game Over - No winner");
        gameOverTarget.setId("gameOverTargetId");
        positionLabelUnderBoard(gameOverPane, gameOverTarget);
    }

    private void unusedTextBoxForConsistantLayout(GridPane boardPane) {
        Label unusedTextForStableLayout = new Label("");
        unusedTextForStableLayout.setId("unusedId");
        positionLabelUnderBoard(boardPane, unusedTextForStableLayout);
    }

    private void registerActionForSelectingGameType(RadioButton radioButton) {
        ClickableElement gameSelectionButton = new JavaFxRadioButton(radioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(controller, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);
    }

    private void registerActionForSelectingDimension(RadioButton radioButton) {
        ClickableElement dimensionSelectionButton = new JavaFxRadioButton(radioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsBoardDimension(controller, dimensionSelectionButton);
        registerClickEvent.register(dimensionSelectionButton, gameSelectionOnClick);
    }

    private void positionLabelUnderBoard(GridPane boardPane, Label label) {
        label.setPrefSize(300, 100);
        boardPane.add(label, 2, 8, 3, 1);
    }
    private Button createButton(String text, String value) {
        Button cell = new Button(text);
        cell.setId(value);
        cell.setMinSize(100, 100);
        return cell;
    }

    private HBox configureHBox(Button cell) {
        HBox gridLayout = new HBox(10);
        gridLayout.setAlignment(CENTER);
        gridLayout.getChildren().add(cell);
        return gridLayout;
    }

    private Function getCellLabelForActiveBoard(Board board) {
        Function<Integer, String> getLabel = index -> {
            PlayerSymbol symbolAtIndex = board.getSymbolAt(index);

            return (symbolAtIndex == VACANT)
                    ? String.valueOf(index + 1)
                    : symbolAtIndex.getSymbolForDisplay();
        };

        return getLabel;
    }

    private Function getCellLabelForDrawnBoard(Board board) {
        Function<Integer, String> getLabel = index -> board.getSymbolAt(index).getSymbolForDisplay();
        return getLabel;
    }

    private void disableOccupied(Button cell) {
        if (Objects.equals(cell.getText(), X.getSymbolForDisplay())
                || Objects.equals(cell.getText(), O.getSymbolForDisplay())) {
            cell.setDisable(true);
        }
    }

    private Function<Button, Void> registerEvent() {
        return button -> {
            disableOccupied(button);

            DeactivatableElement clickableCell = new JavaFxButton(button);
            ClickEvent makeMoveOnClick = new UserSelectsButtonForMove(controller, clickableCell);
            registerClickEvent.register(clickableCell, makeMoveOnClick);
            return null;
        };
    }

    private Function<Button, Void> disable() {
        return button -> {
            DeactivatableElement clickableCell = new JavaFxButton(button);
            clickableCell.setDisabled();
            return null;
        };
    }
}
