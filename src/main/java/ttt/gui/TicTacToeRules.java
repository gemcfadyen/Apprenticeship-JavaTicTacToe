package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;

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
    public void initialiseGame(GameType gameType, String dimension) {
        Integer boardDimension = Integer.valueOf(dimension);
        board = boardFactory.createBoardWithSize(boardDimension);
        players = playerFactory.createPlayers(gameType, boardDimension);
        currentPlayerIndex = PLAYER_ONE_INDEX;
    }

    @Override
    public void takeTurn(int move) {
        board.updateAt(move, players[currentPlayerIndex].getSymbol());
        togglePlayer();
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
    public boolean boardHasFreeSpace() {
        return board.hasFreeSpace();
    }

    @Override
    public int getCurrentPlayersNextMove() {
        return players[currentPlayerIndex].chooseNextMoveFrom(board);
    }

    @Override
    public boolean hasWinner() {
        return board.hasWinningCombination();
    }

    private void togglePlayer() {
        currentPlayerIndex =
                currentPlayerIndex == PLAYER_ONE_INDEX
                        ? PLAYER_TWO_INDEX
                        : PLAYER_ONE_INDEX;
    }


    @Override
    public boolean gameInProgress() {
        return boardHasFreeSpace() && !hasWinner();
    }

    int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
