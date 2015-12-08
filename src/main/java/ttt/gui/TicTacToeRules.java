package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;

import java.util.Arrays;
import java.util.List;

public class TicTacToeRules implements GameRules {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;
    private Board board;
    private Player[] players;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;
    private GameType gameType;

    public TicTacToeRules(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public TicTacToeRules(BoardFactory boardFactory, PlayerFactory playerFactory) {
        this.boardFactory = boardFactory;
        this.playerFactory = playerFactory;
    }

    @Override
    public List<GameType> getGameTypes() {
        //Will return list of game types when gui is expanded to deal with multiple gametypes
        return Arrays.asList(GameType.values());
    }

    @Override
    public String getDimension(GameType gameType) {
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    @Override
    public void initialiseGame(String dimension) {
        Integer boardDimension = Integer.valueOf(dimension);
        board = boardFactory.createBoardWithSize(boardDimension);
        players = playerFactory.createPlayers(gameType, boardDimension);
    }

    @Override
    public void playMoveAt(String move) {
        board.updateAt(Integer.valueOf(move), players[currentPlayerIndex].getSymbol());
    }

    @Override
    public PlayerSymbol getCurrentPlayerSymbol() {
        return players[currentPlayerIndex].getSymbol();
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
    public void storeGameType(GameType gameType) {
        this.gameType = gameType;
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
}
