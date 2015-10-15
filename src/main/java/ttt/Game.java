package ttt;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import static ttt.PlayerSymbol.O;
import static ttt.PlayerSymbol.X;

public class Game {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private final Board board;
    private Prompt gamePrompt;
    private Player[] players;

    public static void main(String... args) {
        Reader commandLineReader = new InputStreamReader(System.in);
        Writer commandLineWriter = new OutputStreamWriter(System.out);
        Prompt prompt = new CommandPrompt(commandLineReader, commandLineWriter);

        HumanPlayer player1 = new HumanPlayer(prompt, X);
        HumanPlayer player2 = new HumanPlayer(prompt, O);

        Game game = new Game(new Board(), prompt, player1, player2);
        game.play();
    }

    public Game(Board board, Prompt gamePrompt, Player player1, Player player2) {
        this.board = board;
        this.gamePrompt = gamePrompt;
        this.players = new Player[]{player1, player2};
    }

    public void play() {
        int index = PLAYER_ONE_INDEX;

        while (board.hasFreeSpace()) {
            updateBoardWithPlayersMove(players[index]);
            if (board.hasWinningCombination()) {
                break;
            }
            index = switchPlayer(index);
        }
        printBoardAndExitMessage();
    }

    private void updateBoardWithPlayersMove(Player player) {
        int nextMove = player.chooseNextMoveFrom(board);
        board.updateAt(nextMove, player.getSymbol());
    }

    private int switchPlayer(int index) {
        return index == PLAYER_ONE_INDEX ? PLAYER_TWO_INDEX : PLAYER_ONE_INDEX;
    }

    private void printBoardAndExitMessage() {
        if (board.hasWinningCombination()) {
            gamePrompt.printWinningMessage();
        } else {
            gamePrompt.printDrawMessage();
        }
        gamePrompt.print(board);
    }
}