package ttt.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import static ttt.player.PlayerSymbol.*;

public class TicTacToeBoardPresenter implements DisplayPresenter {
    private GameTypeController gameTypeController;
    private Scene scene;
    private RegisterClickEvent registerClickEvent;
    private RegisterRollEvent registerRollEvent;
    private GuiGameController controller;
    private final GameTypesPresenter gameTypePresenter;

    public TicTacToeBoardPresenter(GuiGameController controller, GameTypeController gameTypeController, Scene scene, RegisterClickEvent registerClickEvent, RegisterRollEvent registerRollEvent, GameTypesPresenter gameTypePresenter) {
        this.gameTypeController = gameTypeController;
        this.scene = scene;
        this.controller = controller;
        this.registerClickEvent = registerClickEvent;
        this.registerRollEvent = registerRollEvent;
        this.gameTypePresenter = gameTypePresenter;
    }

    @Override
    public void presentGameTypes(List<GameType> gameTypes) {
        gameTypePresenter.presentGameTypes(gameTypes);
    }

    @Override
    public void presentGridDimensionsBetween(int lowerBoundary, int upperBoundary) {
        GridPane dimensionPane = new GridPane();
        setWelcomeMessage(dimensionPane);
        displayDimensions(lowerBoundary, upperBoundary, dimensionPane);

        scene.setRoot(dimensionPane);
    }

    @Override
    public void presentsBoard(Board board) {
        GridPane boardPane = new GridPane();
        gridPaneSetup(boardPane);
        unusedLabelForConsistentLayout(boardPane);

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

    private void displayDimensions(int lowerBoundary, int upperBoundary, GridPane dimensionPane) {
        Text dimensionPrompt = new Text("Choose a board dimension");
        dimensionPrompt.setId("gameSetupId");
        dimensionPane.add(dimensionPrompt, 2, 2, 4, 1);

        int rowIndex = 4;
        for (int dimension = lowerBoundary; dimension <= upperBoundary; dimension++) {
            RadioButton boardDimension = new RadioButton(String.valueOf(dimension));
            boardDimension.setId(String.valueOf(dimension));

            dimensionPane.add(boardDimension, 2, rowIndex++, 4, 1);

            registerActionForSelectingDimension(boardDimension);
        }
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
        Label gameOverTarget = createLabel("Game Over - " + symbol.getSymbolForDisplay() + " won");

        registerActionForReplay(gameOverTarget);

        positionLabelUnderBoard(gameOverPane, gameOverTarget);
    }

    private void drawStatus(GridPane gameOverPane) {
        Label gameOverTarget = createLabel("Game Over - No winner");

        registerActionForReplay(gameOverTarget);

        positionLabelUnderBoard(gameOverPane, gameOverTarget);

    }

    private void unusedLabelForConsistentLayout(GridPane boardPane) {
        Label unusedTextForStableLayout = new Label("");
        unusedTextForStableLayout.setId("unusedId");
        positionLabelUnderBoard(boardPane, unusedTextForStableLayout);
    }

    private void registerActionForSelectingGameType(RadioButton radioButton, RegisterClickEvent registerClickEvent, GuiGameController controller) {
        ClickableElement gameSelectionButton = new JavaFxRadioButton(radioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsGameType(controller, gameSelectionButton);
        registerClickEvent.register(gameSelectionButton, gameSelectionOnClick);
    }

    private void registerActionForSelectingDimension(RadioButton radioButton) {
        ClickableElement dimensionSelectionButton = new JavaFxRadioButton(radioButton);
        ClickEvent gameSelectionOnClick = new UserSelectsBoardDimension(controller, dimensionSelectionButton);
        registerClickEvent.register(dimensionSelectionButton, gameSelectionOnClick);
    }

    private Label createLabel(String text) {
        Label gameOverTarget = new Label(text);
        gameOverTarget.setAlignment(Pos.CENTER);
        gameOverTarget.setId("gameOverTargetId");
        return gameOverTarget;
    }

    private void registerActionForReplay(Label gameOverStatus) {
        JavaFxLabel replay = new JavaFxLabel(gameOverStatus);
        ClickEvent replayOnClick = new UserSelectsReplay(controller, gameTypeController);
        registerClickEvent.register(replay, replayOnClick);

        RollOn textChangesWhenMouseRolledOn = new UserRollsMouseOnGameStatus(replay, "Click To Replay");
        registerRollEvent.register(replay, textChangesWhenMouseRolledOn);

        RollOff textChangesWhenMouseRolledOff = new UserRollsMouseOffGameStatus(replay, replay.getText());
        registerRollEvent.register(replay, textChangesWhenMouseRolledOff);
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
