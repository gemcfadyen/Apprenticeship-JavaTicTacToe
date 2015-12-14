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
    private Board board;
    private Player[] players;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;

    public TicTacToeRules(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public TicTacToeRules(BoardFactory boardFactory, PlayerFactory playerFactory,
                          GameType gameType, int dimension) {
        board = boardFactory.createBoardWithSize(dimension);
        players = playerFactory.createPlayers(gameType, dimension);
    }

    @Override
    public void playMoveAt(String move) {
        board.updateAt(Integer.valueOf(move), players[currentPlayerIndex].getSymbol());
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
    public String getCurrentPlayersNextMove() {
        return String.valueOf(players[currentPlayerIndex].chooseNextMoveFrom(board));
    }

    @Override
    public boolean hasWinner() {
        return board.hasWinningCombination();
    }

    @Override
    public void togglePlayer() {
        currentPlayerIndex =
                currentPlayerIndex == PLAYER_ONE_INDEX
                        ? PLAYER_TWO_INDEX
                        : PLAYER_ONE_INDEX;
    }

    int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
