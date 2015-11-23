package ttt;

import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.ui.CommandPrompt;
import ttt.ui.Prompt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static ttt.ReplayOption.Y;

public class Game {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private final PlayerFactory playerFactory;
    private BoardFactory boardFactory;
    private Board board;
    private Prompt gamePrompt;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;
    private boolean hasWinner = false;

    public Game(Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.board = board;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    public Game(BoardFactory boardFactory, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.boardFactory = boardFactory;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    public static void main(String... args) {
        Game game = new Game(new BoardFactory(), buildPrompt(), new PlayerFactory());
        game.play();
    }

    public void play() {
        ReplayOption replayOption = Y;
        while (replayOption.equals(Y)) {
            Player[] players = setupPlayers();
            playSingleGame(players);
            replayOption = gamePrompt.getReplayOption();
        }
    }

    void playSingleGame(Player[] players) {
        while (gameInProgress(hasWinner)) {
            updateBoardWithPlayersMove(players[currentPlayerIndex]);
            hasWinner = board.hasWinningCombination();
            currentPlayerIndex = toggle(currentPlayerIndex);
        }

        displayResultsOfGame(hasWinner);
        hasWinner = false;
    }

    Player[] setupPlayers() {
        GameType gameType = gamePrompt.getGameType();
        int dimension = getBoardOfCorrectDimensionFor(gameType);
        return createPlayersFor(gameType, dimension);
    }

    int getBoardOfCorrectDimensionFor(GameType gameType) {
        int dimension = gamePrompt.getBoardDimension(gameType);
        board = boardFactory.createBoardWithSize(dimension);
        return dimension;
    }

    boolean gameInProgress(boolean hasWinner) {
        return board.hasFreeSpace() && !hasWinner;
    }

    void updateBoardWithPlayersMove(Player player) {
        int nextMove = player.chooseNextMoveFrom(board);
        board.updateAt(nextMove, player.getSymbol());
    }

    void displayResultsOfGame(boolean hasWinner) {
        gamePrompt.print(board);
        printExitMessage(hasWinner);
    }

    private Player[] createPlayersFor(GameType gameType, int dimension) {
        return playerFactory.createPlayers(gameType, gamePrompt, dimension);
    }

    private int toggle(int currentPlayerIndex) {
        return currentPlayerIndex == PLAYER_ONE_INDEX ? PLAYER_TWO_INDEX : PLAYER_ONE_INDEX;
    }

    private void printExitMessage(boolean hasWinner) {
        if (!hasWinner) {
            gamePrompt.printDrawMessage();
        } else {
            gamePrompt.printWinningMessageFor(board.getWinningSymbol());
        }
    }

    private static CommandPrompt buildPrompt() {
        return new CommandPrompt(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );
    }
}