package ttt.game.rules;

import ttt.game.*;
import ttt.game.board.Board;
import ttt.game.board.BoardFactory;

public class TicTacToeRules implements GameRules {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;
    private Board board;
    private Player[] players;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;

    public TicTacToeRules(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public TicTacToeRules(BoardFactory boardFactory, PlayerFactory playerFactory) {
        this.boardFactory = boardFactory;
        this.playerFactory = playerFactory;
    }

    @Override
    public void initialiseGame(GameType gameType, int dimension) {
        board = boardFactory.createBoardWithSize(dimension);
        players = playerFactory.createPlayers(gameType, dimension);
        currentPlayerIndex = PLAYER_ONE_INDEX;
    }

    @Override
    public void playGame() {
        while (getCurrentPlayer().isReady()
                && gameInProgress()) {
            int currentPlayersNextMove = getCurrentPlayersNextMove();
            takeTurn(currentPlayersNextMove);
        }
    }

    @Override
    public boolean hasWinner() {
        return board.hasWinningCombination();
    }

    @Override
    public boolean noWinnerYet() {
        return !hasWinner();
    }

    @Override
    public PlayerSymbol getWinningSymbol() {
        return board.getWinningSymbol();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean hasAvailableMoves() {
        return board.hasFreeSpace();
    }

    @Override
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    boolean gameInProgress() {
        return hasAvailableMoves() && noWinnerYet();
    }

    private void takeTurn(int move) {
        board.updateAt(move, getCurrentPlayer().getSymbol());
        togglePlayer();
    }

    int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    private int getCurrentPlayersNextMove() {
        return getCurrentPlayer().chooseNextMoveFrom(board);
    }

    private void togglePlayer() {
        currentPlayerIndex =
                currentPlayerIndex == PLAYER_ONE_INDEX
                        ? PLAYER_TWO_INDEX
                        : PLAYER_ONE_INDEX;
    }
}
