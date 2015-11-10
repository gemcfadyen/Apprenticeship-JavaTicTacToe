package ttt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static ttt.ReplayOptions.*;

public class Game {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private final PlayerFactory playerFactory;
    private Board board;
    private Prompt gamePrompt;

    public Game(Board board, Prompt gamePrompt, PlayerFactory playerFactory) {
        this.board = board;
        this.gamePrompt = gamePrompt;
        this.playerFactory = playerFactory;
    }

    public static void main(String... args) {
        Prompt prompt = buildPrompt();

        Game game = new Game(new Board(), prompt, new PlayerFactory());

        game.play();
    }

    public void play() {
        String replayOption = Y.name();
        while (replayOption.equals(Y.name())) {
            replayOption = playSingleGame();
            reinitialiseBoard();
        }
    }

    private String playSingleGame() {
        int currentPlayerIndex = PLAYER_ONE_INDEX;
        boolean hasWinner = false;

        Player[] players = createPlayers();

        while (gameInProgress(hasWinner)) {
            updateBoardWithPlayersMove(players[currentPlayerIndex]);
            hasWinner = board.hasWinningCombination();
            currentPlayerIndex = toggle(currentPlayerIndex);
        }

        displayResultsOfGame(hasWinner);
        return gamePrompt.getReplayOption();
    }

    private Player[] createPlayers() {
        int playerOption = gamePrompt.getGameType();
        return playerFactory.createPlayers(playerOption, gamePrompt);
    }

    private boolean gameInProgress(boolean hasWinner) {
        return board.hasFreeSpace() && !hasWinner;
    }

    private void updateBoardWithPlayersMove(Player player) {
        int nextMove = player.chooseNextMoveFrom(board);
        board.updateAt(nextMove, player.getSymbol());
    }

    private int toggle(int currentPlayerIndex) {
        return currentPlayerIndex == PLAYER_ONE_INDEX ? PLAYER_TWO_INDEX : PLAYER_ONE_INDEX;
    }

    private void reinitialiseBoard() {
        board = new Board();
    }

    private void displayResultsOfGame(boolean hasWinner) {
        gamePrompt.print(board);
        printExitMessage(hasWinner);
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