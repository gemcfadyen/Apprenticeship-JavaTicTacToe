package ttt.gui;

import javafx.scene.Scene;
import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;
import ttt.ui.WritePromptForGuiNew;

public class TicTacToeBoardController implements TTTController {

    private WritePromptForGuiNew view;
    private GameRules model;

    //pass in a viewfactory which takes scene as a constructor arg, then for testsi can dummy it out
    public TicTacToeBoardController(GameRules model, Scene scene) {
        this.model = model;
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
    public void presentBoard(String dimensionForBoard) {
        model.initialiseGame(Integer.valueOf(dimensionForBoard).intValue());
        Board board = model.getBoard();
        view.printBoard(board);
    }

    @Override
    public void playMove(String id) {

        PlayerSymbol symbol = model.getCurrentPlayer();

        // model.playMoveAt(id)
//        view.printBoard();

        model.playMoveAt(id);
        Board board = model.getBoard();
        view.printBoard(board);
        if(model.hasWinner()) {
            view.printWinningMessageFor(symbol, board);
        } else if(!model.boardHasFreeSpace()) {
            view.printDrawMessage(board);
        }
        model.togglePlayer();
    }


}
