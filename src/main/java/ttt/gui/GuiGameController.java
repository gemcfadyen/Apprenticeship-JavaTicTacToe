package ttt.gui;

import ttt.GameType;
import ttt.board.Board;

import java.util.List;

import static ttt.player.GuiHumanPlayer.PLACEHOLDER_UNUSED_MOVE;

public class GuiGameController implements GameController {

    private final GameConfiguration gameConfiguration;
    private GameRules ticTacToeRules;
    private DisplayPresenter boardView;
    private GameType gameType;

    public GuiGameController(GameConfiguration gameConfiguration, GameRules ticTacToeRules, ViewFactory viewFactory) {
        this.gameConfiguration = gameConfiguration;
        this.ticTacToeRules = ticTacToeRules;
        this.boardView = viewFactory.createView(this, ticTacToeRules);
    }

    @Override
    public void presentGameTypes() {
        List<GameType> allGameTypes = gameConfiguration.getGameTypes();
        boardView.presentGameTypes(allGameTypes);
    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        setGameType(gameType);
        String dimension = gameConfiguration.getDimension(gameType);
        boardView.presentGridDimensionsUpTo(dimension);
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
        playMoveIfSpaceOnBoard(position);
        playAutomatedMoveIfAppropriate();
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    private void playAutomatedMoveIfAppropriate() {
        String automatedMove = ticTacToeRules.getCurrentPlayersNextMove();
        if (Integer.valueOf(automatedMove) != PLACEHOLDER_UNUSED_MOVE) {
            playMoveIfSpaceOnBoard(automatedMove);
        }
    }

    private void playMoveIfSpaceOnBoard(String currentPlayersNextMove) {
        if (ticTacToeRules.gameInProgress()) {
            playTurn(currentPlayersNextMove);
        }
    }

    private void playTurn(String currentPlayersNextMove) {
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
