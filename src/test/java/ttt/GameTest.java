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
        PlayerSpy player1 = new PlayerSpy(createCommandPromptToReadInput("0\n4\n5\n6\n7\n"), X);
        PlayerSpy player2 = new PlayerSpy(createCommandPromptToReadInput("1\n2\n3\n8\n"), O);

        Game game = new Game(new Board(),
                createCommandPromptToReadInput(""),
                player1,
                player2);

        game.play();

        assertThat(player1.numberOfTurnsTaken(), is(5));
        assertThat(player2.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void displaysCongratulatoryMessageWhenThereIsAWinningFormation() {
        PromptSpy gamePrompt = new PromptSpy();

        Game game = new Game(new Board(),
                gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("0\n1\n2\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("4\n7\n"), O));

        game.play();

        assertThat(gamePrompt.getNumberOfTimesWinningMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(0));
    }

    @Test
    public void displaysDrawMessageWhenThereAreNoMoreSpacesOnTheBoardAndNoWinner() {
        Board board = new Board(X, O, O, O, X, X, VACANT, VACANT, O);
        PromptSpy gamePrompt = new PromptSpy();

        Game game = new Game(board,
                gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("7\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("6\n"), O));

        game.play();

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getNumberOfTimesWinningMessageHasBeenPrinted(), is(0));
    }

    @Test
    public void displaysTheFinalWinningStateOfTheBoard() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy();

        Game game = new Game(board, gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("1\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("\n"), O));

        game.play();

        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOXOOOX"));
    }

    @Test
    public void displaysTheFinalDrawnStateOfTheBoard() {
        Board board = new Board(X, O, O, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy();

        Game game = new Game(board,
                gamePrompt,
                createHumanPlayer(createCommandPromptToReadInput("\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("\n"), O));

        game.play();

        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XOOOXOOOX"));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        return new HumanPlayer(prompt, symbol);
    }
}