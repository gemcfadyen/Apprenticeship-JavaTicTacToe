package ttt.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ttt.GameType;
import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;
import ttt.ui.WritePromptForGuiNew;

import java.util.List;
import java.util.function.Function;

public class TicTacToeBoardController implements TTTController {

    private WritePromptForGuiNew view;
    private GameRules model;

    private Scene scene;

    public TicTacToeBoardController(GameRules model, Scene scene) {
        this.model = model;
        this.scene = scene;
        this.view = new TTTView(this, scene);
    }

    public void presentGameTypes() {

        GameType gameType = model.getGameTypes();
        view.presentGameTypes(gameType.gameNameForDisplay());
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {

        String dimension = model.getDimension(gameType);
        view.presentBoardDimensions(dimension);
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        Board board = model.getBoard();
        printBoardsOnPane(board, gameOverPane, getCellLabelForWinningBoard(board), disable());

        Text gameOverTarget = new Text("Game Over, " + symbol.getSymbolForDisplay() + " won");
        gameOverTarget.setId("gameOverTargetId");
        gameOverPane.add(gameOverTarget, 2, 7, 6, 1);

        scene.setRoot(gameOverPane);
    }

    @Override
    public void presentBoard(String dimensionForBoard) {
        model.initialiseGame(Integer.valueOf(dimensionForBoard).intValue());
        Board board = model.getBoard();
        view.printBoard(board);
    }

    @Override
    public void playMove(String id) {

        PlayerSymbol symbol = model.getCurrentPlayer();

        model.playMoveAt(id);
        Board board = model.getBoard();
        view.printBoard(board);
//        view.updateBoardWith(symbol);
        if(model.hasWinner()) {
            view.printWinningMessageFor(symbol);
        } else if(!model.boardHasFreeSpace()) {
            view.printDrawMessage();
        }
        model.togglePlayer();

    }

    public void printDrawMessage() {
        GridPane gameOverPane = new GridPane();
        gridPaneSetup(gameOverPane);

        Board board = model.getBoard();
        printBoardsOnPane(board, gameOverPane, getCellLabelForDrawnBoard(board), disable());

        Text gameOverTarget = new Text("Game Over, No winner");
        gameOverTarget.setId("gameOverTargetId");
        gameOverPane.add(gameOverTarget, 2, 7, 6, 1);
        scene.setRoot(gameOverPane);
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

    private Function<Button, Void> disable() {
        return button -> {
            DeactivatableElement clickableCell = new JavaFxButton(button);
            clickableCell.setDisabled();
            return null;
        };

    }

}
