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
        Prompt promptForPlayerOne = new CommandPrompt(new StringReader("0\n4\n5\n6\n7\n"), new StringWriter());
        Prompt promptForPlayerTwo = new CommandPrompt(new StringReader("1\n2\n3\n8\n"), new StringWriter());

        PlayerSpy player1Spy = new PlayerSpy(promptForPlayerOne, X);
        PlayerSpy player2Spy = new PlayerSpy(promptForPlayerTwo, O);

        StringWriter gamePromptWriter = new StringWriter();
        Prompt gamePrompt = new CommandPrompt(new StringReader(""), gamePromptWriter);
        Game game = new Game(new Board(), gamePrompt, player1Spy, player2Spy);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void displaysCongratulatoryMessageWhenThereIsAWinningFormation() {
        Prompt promptForPlayerOne = new CommandPrompt(new StringReader("0\n1\n2\n"), new StringWriter());
        Prompt promptForPlayerTwo = new CommandPrompt(new StringReader("4\n7\n"), new StringWriter());
        StringWriter gamePromptWriter = new StringWriter();
        Prompt gamePrompt = new CommandPrompt(new StringReader(""), gamePromptWriter);

        HumanPlayer player1 = new HumanPlayer(promptForPlayerOne, X);
        HumanPlayer player2 = new HumanPlayer(promptForPlayerTwo, O);

        Game game = new Game(new Board(), gamePrompt, player1, player2);

        game.play();

        assertThat(gamePromptWriter.toString(), is("Congratulations - There is a winner"));
    }

}