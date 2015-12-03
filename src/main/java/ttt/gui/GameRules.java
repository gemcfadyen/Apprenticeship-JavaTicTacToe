package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

public class GameRules implements GameInterface {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;
    private Board board;
    private Player[] players;
    private GameType gameType;

    GameRules(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }


    public GameRules(PlayerFactory playerFactory,
                     Prompt gamePrompt,
                     BoardFactory boardFactory) {
        this.playerFactory = playerFactory;
        this.boardFactory = boardFactory;

    }

    public void playMoveAt(String move) {
        board.updateAt(Integer.valueOf(move), players[currentPlayerIndex].getSymbol());
//        if (board.hasWinningCombination()) {
//            prompt.printWinningMessageFor(getCurrentPlayer());
//        } else if (!board.hasFreeSpace()) {
//            prompt.printDrawMessage();
//        }
//        currentPlayerIndex = togglePlayer();
    }

    public PlayerSymbol getCurrentPlayer() {
        return players[currentPlayerIndex].getSymbol();
    }

    public boolean hasWinner() {
        return board.hasWinningCombination();
    }

    public boolean boardHasFreeSpace() {
        return board.hasFreeSpace();
    }

    public void togglePlayer() {
        currentPlayerIndex =
                currentPlayerIndex == PLAYER_ONE_INDEX
                        ? PLAYER_TWO_INDEX
                        : PLAYER_ONE_INDEX;
    }

    @Override
    public void createBoard(int dimension) {
        board = boardFactory.createBoardWithSize(dimension);
        players = playerFactory.createPlayers(gameType, null, dimension);
    }

    //TODO added in spike mode
    public Board getBoard() {
        return board;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
}
