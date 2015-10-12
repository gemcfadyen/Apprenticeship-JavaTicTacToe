package ttt;

import java.io.*;

/**
 * Created by Georgina on 09/10/15.
 */
public class Game {
    private final HumanPlayer[] players;
    private Board board;

    public Game(Board board, HumanPlayer... players) {
        this.board = board;
        this.players = players;
    }

    public static void main(String... args) throws IOException {
        Reader reader1 = new InputStreamReader(System.in);
        Reader reader2 = new InputStreamReader(System.in);
        OutputStreamWriter writer1 = new OutputStreamWriter(System.out);
        OutputStreamWriter writer2 = new OutputStreamWriter(System.out);
        Prompt promptForPlayerOne = new UserPrompt(reader1, writer1);
        Prompt promptForPlayerTwo = new UserPrompt(reader2, writer2);

        Game game = new Game(new Board(),
                new HumanPlayer(promptForPlayerOne, "X"),
                new HumanPlayer(promptForPlayerTwo, "O"));
        game.play();
    }

    public String play() throws IOException {

        int playerIndex = 0;
        while (board.hasFreeSpace()) {

            int nextMove = players[playerIndex].chooseNextMoveFrom(board);

            board.updateAt(nextMove, players[playerIndex].getSymbols());
            playerIndex = toggle(playerIndex);

            if (board.hasWinningCombination()) {
                return "Win";
            }
        }
        return "Draw";
    }

    private int toggle(int playerIndex) {
        return playerIndex == 0 ? 1 : 0;
    }
}
