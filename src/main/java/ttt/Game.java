package ttt;

import java.io.*;

/**
 * Created by Georgina on 09/10/15.
 */
public class Game {
    private Board board;
    private final HumanPlayer[] players;
    private Prompt prompt;

    public Game(Board board, Prompt prompt, HumanPlayer... players) {
        this.board = board;
        this.prompt = prompt;
        this.players = players;
    }

    public static void main(String... args) throws IOException {
        Prompt prompt = createPrompt();

        Game game = new Game(new Board(),
                prompt,
                new HumanPlayer(createPrompt(), "X"),
                new HumanPlayer(createPrompt(), "O"));
        game.play();
    }

    private static Prompt createPrompt() {
        Reader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        return new UserPrompt(reader, writer);
    }

    public String play() throws IOException {

        int playerIndex = 0;
        while (board.hasFreeSpace()) {

            int nextMove = players[playerIndex].chooseNextMoveFrom(board);

            board.updateAt(nextMove, players[playerIndex].getSymbols());
            playerIndex = toggle(playerIndex);

            if (board.hasWinningCombination()) {
                prompt.print(board);
                prompt.printWinningMessage();
                return "Win";
            }
        }
        prompt.print(board);
        prompt.printDrawMessage();
        return "Draw";
    }

    private int toggle(int playerIndex) {
        return playerIndex == 0 ? 1 : 0;
    }
}
