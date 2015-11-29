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
    private PlayerFactory playerFactory;
    private BoardFactory boardFactory;
    private Board board;
    private Prompt gamePrompt;
    private int currentPlayerIndex = PLAYER_ONE_INDEX;

    public Game() {

    }

    Game(Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
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
            playMatch(players);
            replayOption = gamePrompt.getReplayOption();
        }
    }

    void playMatch(Player[] players) {
        while (gameInProgress()) {
            updateBoardWithPlayersMove(players[currentPlayerIndex]);
            currentPlayerIndex = toggle(currentPlayerIndex);
        }
        displayResultsOfGame();
    }

    boolean gameInProgress() {
        return board.hasFreeSpace() && !board.hasWinningCombination();
    }

    void updateBoardWithPlayersMove(Player player) {
        int nextMove = player.chooseNextMoveFrom(board);
        board.updateAt(nextMove, player.getSymbol());
    }

    Player[] setupPlayers() {
        gamePrompt.presentGameTypes();
        GameType gameType = gamePrompt.readGameType();
        int dimension = getBoardOfCorrectDimensionFor(gameType);
        return createPlayersFor(gameType, dimension);
    }

    void displayResultsOfGame() {
        gamePrompt.print(board);
        printExitMessage(board.hasWinningCombination());
    }

    int getBoardOfCorrectDimensionFor(GameType gameType) {
        gamePrompt.presentBoardDimensionsFor(gameType);
        int dimension = gamePrompt.readBoardDimension(gameType);
        board = boardFactory.createBoardWithSize(dimension);
        return dimension;
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