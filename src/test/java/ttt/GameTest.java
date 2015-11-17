package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.player.HumanPlayer;
import ttt.player.PlayerSymbol;
import ttt.ui.CommandPrompt;
import ttt.ui.Prompt;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class GameTest {

    @Test
    public void printsChoiceOfPlayers() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader("1\nN\n"));

        Game game = new Game(new Board(), gamePrompt, new PlayerFactorySpy(
                new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n")),
                new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n")))
        );

        game.play();

        assertThat(gamePrompt.getNumberOfTimesPromptedForPlayerOption(), is(1));
    }

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n"));
        PlayerSpy player2Spy = new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n"));
        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy(
                player1Spy,
                player2Spy
        );

        Game game = new Game(new Board(),
                createCommandPromptToReadInput("1\nN\n"),
                playerFactorySpy);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void printsCongratulatoryMessageWhenThereIsAWinningFormation() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader("1\nN\n"));

        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy(
                createHumanPlayer(createCommandPromptToReadInput("1\n2\n3\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("5\n8\n"), O)
        );

        Game game = new Game(new Board(),
                gamePrompt,
                playerFactorySpy);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(0));
    }

    @Test
    public void printsDrawMessageWhenThereAreNoMoreSpacesOnTheBoardAndNoWinner() {
        Board board = new Board(X, O, O, O, X, X, VACANT, VACANT, O);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("1\nN\n"));

        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy(
                createHumanPlayer(createCommandPromptToReadInput("8\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("7\n"), O)
        );

        Game game = new Game(board,
                gamePrompt,
                playerFactorySpy);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(0));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
    }

    @Test
    public void printsTheFinalStateOfTheBoard() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("1\nN\n"));

        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy(
                createHumanPlayer(createCommandPromptToReadInput("2\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("\n"), O)
        );

        Game game = new Game(board, gamePrompt, playerFactorySpy);

        game.play();

        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOXOOOX"));
    }

    @Test
    public void promptsToPlayAgain() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader("1\nY\n1\nN\n"));

        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy(
                createHumanPlayer(createCommandPromptToReadInput("2\n2\n3\n5\n"), X),
                createHumanPlayer(createCommandPromptToReadInput("1\n4\n7\n"), O)
        );

        Game game = new Game(board, gamePrompt, playerFactorySpy);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(2));
        assertThat(gamePrompt.getNumberOfTimesPromptedForPlayerOption(), is(2));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        return new HumanPlayer(symbol, prompt);
    }
}