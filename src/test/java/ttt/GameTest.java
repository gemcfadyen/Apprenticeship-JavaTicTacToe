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

    public static final String DIMENSION = "3\n";
    public static final String HUMAN_VS_HUMAN = "1\n";
    public static final String DO_NOT_REPLAY = "N\n";
    public static final String REPLAY = "Y\n";

    @Test
    public void promptsForBoardDimension() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY));
        BoardFactoryStub boardFactoryStub = new BoardFactoryStub(new Board(3));

        Game game = new Game(boardFactoryStub, gamePrompt, new PlayerFactoryStub(
                new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n")),
                new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n")))
        );

        game.play();

        assertThat(gamePrompt.getNumberOfTimesBoardDimensionPrompted(), is(1));
        assertThat(boardFactoryStub.getDimension(), is(3));
    }

    @Test
    public void printsChoiceOfPlayers() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(DIMENSION + HUMAN_VS_HUMAN + DO_NOT_REPLAY));

        Game game = new Game(new BoardFactoryStub(new Board(3)), gamePrompt, new PlayerFactoryStub(
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
        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                player1Spy,
                player2Spy
        );

        Game game = new Game(new BoardFactoryStub(new Board(3)),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY),
                playerFactoryStub);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void printsCongratulatoryMessageWhenThereIsAWinningFormation() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY));

        PlayerFactoryStub playerFactorySpy = new PlayerFactoryStub(
                createHumanPlayer(X, createCommandPromptToReadInput("1\n2\n3\n")),
                createHumanPlayer(O, createCommandPromptToReadInput("5\n8\n"))
        );

        Game game = new Game(new BoardFactoryStub(new Board(3)),
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
        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY));

        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                createHumanPlayer(X, createCommandPromptToReadInput("8\n")),
                createHumanPlayer(O, createCommandPromptToReadInput("7\n"))
        );

        Game game = new Game(new BoardFactoryStub(board),
                gamePrompt,
                playerFactoryStub);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(0));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
    }

    @Test
    public void printsTheFinalStateOfTheBoard() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY));

        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                createHumanPlayer(X, createCommandPromptToReadInput("2\n")),
                createHumanPlayer(O, createCommandPromptToReadInput("\n"))
        );

        Game game = new Game(new BoardFactoryStub(board), gamePrompt, playerFactoryStub);

        game.play();

        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOXOOOX"));
    }

    @Test
    public void promptsToPlayAgain() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION + REPLAY +  HUMAN_VS_HUMAN + DIMENSION + DO_NOT_REPLAY));

        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                createHumanPlayer(X, createCommandPromptToReadInput("2\n2\n3\n5\n")),
                createHumanPlayer(O, createCommandPromptToReadInput("1\n4\n7\n"))
        );

        Game game = new Game(new BoardFactoryStub(board, new Board(3)), gamePrompt, playerFactoryStub);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(2));
        assertThat(gamePrompt.getNumberOfTimesPromptedForPlayerOption(), is(2));
        assertThat(gamePrompt.getNumberOfTimesBoardDimensionPrompted(), is(2));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}