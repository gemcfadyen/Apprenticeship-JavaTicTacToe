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
    private final BoardFactory boardFactory;
    private Board board;
    private Prompt gamePrompt;

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
            replayOption = playSingleGame();
        }
    }

    private ReplayOption playSingleGame() {
        int currentPlayerIndex = PLAYER_ONE_INDEX;
        boolean hasWinner = false;

        int dimension = gamePrompt.getBoardDimension();
        board = boardFactory.createBoardWithSize(dimension);
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
        GameType playerOption = gamePrompt.getGameType();
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