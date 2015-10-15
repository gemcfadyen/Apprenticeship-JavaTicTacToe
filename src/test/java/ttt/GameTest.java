package ttt;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.O;
import static ttt.PlayerSymbol.X;

public class GameTest {

    @Test
    public void playersTakeTurnUntilTheBoardIsFull() {
        Board board = new Board();
        Prompt promptForPlayerOne = new UserPrompt(new StringReader("0\n4\n5\n6\n7\n"), new StringWriter());
        Prompt promptForPlayerTwo = new UserPrompt(new StringReader("1\n2\n3\n8\n"), new StringWriter());


        PlayerSpy player1Spy = new PlayerSpy(promptForPlayerOne, X);
        PlayerSpy player2Spy = new PlayerSpy(promptForPlayerTwo, O);
        Game game = new Game(board, player1Spy, player2Spy);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }


}