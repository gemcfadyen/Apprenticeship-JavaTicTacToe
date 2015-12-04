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
import java.util.function.Function;

public class TicTacToeBoardPresenter implements BoardPresenter {
    private RadioButton humanVsHumanRadioButton;
    private Scene scene;
    private GameRulesPrompt guiPrompt;
    private RegisterClickEvent registerClickEvent;
    private GuiGameController controller;

    public TicTacToeBoardPresenter(TicTacToeRules ticTacToeRules, Scene scene) {
        this.scene = scene;
        guiPrompt = new GuiPrompt(this, ticTacToeRules);
        registerClickEvent = new RegisterClickEvent();
    }

    public TicTacToeBoardPresenter(GameRules gameRules, GuiGameController controller, Scene scene) {
        this.scene = scene;
//        this.gamesRules = gameRules;
        this.controller = controller;
    }

    @Override
    public void presentGameTypes(String gameType) {
        GridPane gameTypePane = new GridPane();
        setWelcomeMessage(gameTypePane);

        displayGameTypes(gameTypePane, gameType);

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
        printBoardsOnPane(board, boardPane, getCellLabelForInitialBoard(), registerEvent());

        scene.setRoot(boardPane);
    }

    @Override
    public void printsWinning(Board board, PlayerSymbol symbol) {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        printBoardsOnPane(board, gameOverPane, getCellLabelForWinningBoard(board), disable());

        Text gameOverTarget = new Text("Game Over, " + symbol.getSymbolForDisplay() + " won");
        gameOverTarget.setId("gameOverTargetId");
        gameOverPane.add(gameOverTarget, 2, 7, 6, 1);

        scene.setRoot(gameOverPane);
    }

    @Override
    public void printsDraw(Board board) {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        printBoardsOnPane(board, gameOverPane, getCellLabelForDrawnBoard(board), disable());

        Text gameOverTarget = new Text("Game Over, No winner");
        gameOverTarget.setId("gameOverTargetId");
        gameOverPane.add(gameOverTarget, 2, 7, 6, 1);
        scene.setRoot(gameOverPane);
    }

    private void setWelcomeMessage(GridPane gridPane) {
        Text gameTitle = new Text("Tic Tac Toe");
        gameTitle.setId("gameTitleId");
        gridPane.add(gameTitle, 0, 0, 6, 1);
        gridPaneSetup(gridPane);
    }

    private void displayGameTypes(GridPane gridPane, String gameType) {
        Text gameSelectionPrompt = new Text("Choose a game type");
        gameSelectionPrompt.setId("gameSetupId");
        gridPane.add(gameSelectionPrompt, 2, 2, 4, 1);
        humanVsHumanRadioButton = new RadioButton(gameType);
        humanVsHumanRadioButton.setId("gameSetupSelectionId");
        gridPane.add(humanVsHumanRadioButton, 2, 4, 4, 1);
    }

    private void gridPaneSetup(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
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

    private Function<Integer, String> getCellLabelForInitialBoard() {
        return index -> String.valueOf(index + 1);
    }

    private Function getCellLabelForDrawnBoard(Board board) {
        Function<Integer, String> getLabel = index -> board.getSymbolAt(index).getSymbolForDisplay();
        return getLabel;
    }

    private Function getCellLabelForWinningBoard(Board board) {
        Function<Integer, String> getLabel = index -> {
            PlayerSymbol symbolAtIndex = board.getSymbolAt(index);

            return (symbolAtIndex == PlayerSymbol.VACANT)
                    ? String.valueOf(index + 1)
                    : symbolAtIndex.getSymbolForDisplay();

        };

        return getLabel;
    }

    private Function<Button, Void> registerEvent() {
        return button -> {
            DeactivatableElement clickableCell = new JavaFxButton(button);
            ClickEvent makeMoveOnClick = new UserSelectsButtonForMove(guiPrompt, clickableCell);
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
