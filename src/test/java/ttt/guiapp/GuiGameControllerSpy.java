package ttt.guiapp;

import ttt.game.GameController;
import ttt.game.GameType;
import ttt.game.Player;

public class GuiGameControllerSpy implements GameController {
    private boolean hasPresentedBoardDimensions = false;
    private GameType gameType;
    private boolean hasPresentedBoard = false;
    private int boardDimension = 0;
    private boolean hasTakenMove = false;
    private boolean hasPresentedGameTypes = false;

    @Override
    public void presentGameTypes() {
        hasPresentedGameTypes = true;
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        this.gameType = gameType;
        hasPresentedBoardDimensions = true;
    }

    @Override
    public void startGame(int dimensionForBoard) {
        hasPresentedBoard = true;
        boardDimension = Integer.valueOf(dimensionForBoard);
    }

    @Override
    public void takeMove(int position) {
        hasTakenMove = true;
    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    public boolean hasPresentedGameTypes() {
        return hasPresentedGameTypes;
    }

    public boolean hasPresentedBoardDimensions() {
        return hasPresentedBoardDimensions;
    }

    public GameType capturedGameType() {
        return gameType;
    }

    public boolean hasPresentedBoard() {
        return hasPresentedBoard;
    }

    public int boardSize() {
        return boardDimension;
    }

    public boolean hasTakenMove() {
        return hasTakenMove;
    }
}
