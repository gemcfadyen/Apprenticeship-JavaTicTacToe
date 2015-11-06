package ttt;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class GameTest {

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1 = new PlayerSpy(createCommandPromptToReadInput("1\n5\n6\n7\n8\n"), X);
        PlayerSpy player2 = new PlayerSpy(createCommandPromptToReadInput("2\n3\n4\n9\n"), O);

        Game game = new Game(new Board(),
                createCommandPromptToReadInput("N\n"),
                player1,
                player2);

        game.play();

        assertThat(player1.numberOfTurnsTaken(), is(5));
        assertThat(player2.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void printsCongratulatoryMessageWhenThereIsAWinningFormation() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader("N\n"));

        Game game = new Game(new Board(),
                gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("1\n2\n3\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("5\n8\n"), O));

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(0));
    }

    @Test
    public void printsDrawMessageWhenThereAreNoMoreSpacesOnTheBoardAndNoWinner() {
        Board board = new Board(X, O, O, O, X, X, VACANT, VACANT, O);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("N\n"));

        Game game = new Game(board,
                gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("8\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("7\n"), O));

        game.play();

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(0));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
    }

    @Test
    public void printsTheFinalStateOfTheBoard() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("N\n"));

        Game game = new Game(board, gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("2\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("\n"), O));

        game.play();

        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOXOOOX"));
    }

    @Test
    public void promptsToPlayAgain() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("Y\nN\n"));

        Game game = new Game(board, gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("2\n2\n3\n5\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("1\n4\n7\n"), O));

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(2));
        assertThat(gamePrompt.getNumberOfTimesClearIsCalled(), is(2));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        return new HumanPlayer(prompt, symbol);
    }
}