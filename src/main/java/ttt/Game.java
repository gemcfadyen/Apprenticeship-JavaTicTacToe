package ttt;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

import static ttt.PlayerSymbol.O;
import static ttt.PlayerSymbol.X;

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
                new HumanPlayer(createPrompt(), X),
                new HumanPlayer(createPrompt(), O));
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

            int nextMove = getNextMoveFrom(players[playerIndex]);

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

    private int getNextMoveFrom(HumanPlayer player) throws IOException {
        return player.chooseNextMoveFrom(board);
    }

    private int toggle(int playerIndex) {
        return playerIndex == 0 ? 1 : 0;
    }
}
