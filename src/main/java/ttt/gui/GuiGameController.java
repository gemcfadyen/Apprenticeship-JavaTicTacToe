package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

import java.util.List;

import static ttt.player.GuiHumanPlayer.IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;

public class GuiGameController implements GameController {

    private final GameConfiguration gameConfiguration;
    private GameRules ticTacToeRules;
    private DisplayPresenter boardView;
    private GameType gameType;

    private GuiGameController(GameConfiguration gameConfiguration, GameRules ticTacToeRules, ViewFactory viewFactory) {
        this.gameConfiguration = gameConfiguration;
        this.ticTacToeRules = ticTacToeRules;
    }

    public static GuiGameController createGuiGameController(GameConfiguration gameConfiguration, GameRules ticTacToeRules, ViewFactory viewFactory) {
        GuiGameController guiGameController = new GuiGameController(gameConfiguration, ticTacToeRules, viewFactory);
        DisplayPresenter view = viewFactory.createView(guiGameController, ticTacToeRules);
        guiGameController.setBoardPresenter(view);

        return guiGameController;
    }

    private void setBoardPresenter(DisplayPresenter view) {
        this.boardView = view;
    }

    @Override
    public void presentGameTypes() {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        boardView.presentGameTypes(allGameTypes);
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        setGameType(gameType);
        boardView.presentGridDimensionsBetween(gameType.dimensionLowerBoundary(), gameType.dimensionUpperBoundary());
    }

    @Override
    public void presentBoard(String dimension) {
        ticTacToeRules.initialiseGame(gameType, dimension);
        Board board = ticTacToeRules.getBoard();

        playAutomatedMoveIfAppropriate();
        boardView.presentsBoard(board);
    }

    @Override
    public void playMove(String position) {
        playMoveIfSpaceOnBoard(Integer.valueOf(position));
        playAutomatedMoveIfAppropriate();
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    private void playAutomatedMoveIfAppropriate() {
        int move = ticTacToeRules.getCurrentPlayersNextMove();
        if (nonInteractiveMove(move)) {
            playMoveIfSpaceOnBoard(move);
        }
    }

    private boolean nonInteractiveMove(int automatedMove) {
        return automatedMove != IGNORE_AS_MOVE_WILL_COME_FROM_DISPLAY;
    }

    private void playMoveIfSpaceOnBoard(int currentPlayersNextMove) {
        if (ticTacToeRules.gameInProgress()) {
            playTurn(currentPlayersNextMove);
        }
    }

    private void playTurn(int currentPlayersNextMove) {
        ticTacToeRules.takeTurn(currentPlayersNextMove);
        Board board = presentBoard();
        displayExitMessage(board);
    }

    private Board presentBoard() {
        Board board = ticTacToeRules.getBoard();
        boardView.presentsBoard(board);
        return board;
    }

    GameType getGameType() {
        return gameType;
    }

    private void displayExitMessage(Board board) {
        if (ticTacToeRules.hasWinner()) {
            boardView.printsWinningMessage(board, ticTacToeRules.getWinningSymbol());
        } else if (!ticTacToeRules.boardHasFreeSpace()) {
            boardView.printsDrawMessage(board);
        }
    }
}
