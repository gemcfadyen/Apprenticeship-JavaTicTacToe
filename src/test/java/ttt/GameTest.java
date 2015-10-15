package ttt;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class GameTest {

    @Test
    public void playersTakeTurnUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(createCommandPromptToReadInput("0\n4\n5\n6\n7\n"), X);
        PlayerSpy player2Spy = new PlayerSpy(createCommandPromptToReadInput("1\n2\n3\n8\n"), O);

        StringWriter gamePromptWriter = new StringWriter();
        Game game = new Game(new Board(),
                createCommandPromptWithWriter(gamePromptWriter),
                player1Spy,
                player2Spy);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    private CommandPrompt createCommandPromptWithWriter(StringWriter gamePromptWriter) {
        return new CommandPrompt(new StringReader(""), gamePromptWriter);
    }

    @Test
    public void displaysCongratulatoryMessageWhenThereIsAWinningFormation() {
        Prompt promptForPlayerOne = new CommandPrompt(new StringReader("0\n1\n2\n"), new StringWriter());
        Prompt promptForPlayerTwo = new CommandPrompt(new StringReader("4\n7\n"), new StringWriter());
        StringWriter gamePromptWriter = new StringWriter();

        Game game = new Game(new Board(),
                createCommandPromptWithWriter(gamePromptWriter),
                createHumanPlayer(promptForPlayerOne, X),
                new HumanPlayer(promptForPlayerTwo, O));

        game.play();

        assertThat(gamePromptWriter.toString(), is("Congratulations - There is a winner"));
    }

    @Test
    public void displaysNoWinnerMessageWhenThereAreNoMoreSpacesOnTheBoardAndNoWinner() {
        Board board = new Board(X, O, O, O, X, X, VACANT, VACANT, O);
        Prompt promptForPlayerOne = new CommandPrompt(new StringReader("7\n"), new StringWriter());
        Prompt promptForPlayerTwo = new CommandPrompt(new StringReader("6\n"), new StringWriter());
        StringWriter gamePromptWriter = new StringWriter();

        Game game = new Game(board,
                createCommandPromptWithWriter(gamePromptWriter),
                createHumanPlayer(promptForPlayerOne, X),
                createHumanPlayer(promptForPlayerTwo, O));

        game.play();

        assertThat(gamePromptWriter.toString(), is("No winner this time"));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        return new HumanPlayer(prompt, symbol);
    }


}