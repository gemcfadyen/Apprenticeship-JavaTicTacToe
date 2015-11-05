package ttt;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class HumanPlayerTest {

    @Test
    public void getThePlayersSymbol() {
        PromptSpy prompt = new PromptSpy(new StringReader("2\n"));
        HumanPlayer player = new HumanPlayer(prompt, X);

        assertThat(player.getSymbol(), is(X));
    }

    @Test
    public void playerProvidesPromptWithNextMove() throws IOException {
        PromptSpy prompt = new PromptSpy(new StringReader("2\n"));
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void playerRepromptedUntilValidNumberIsEntered() throws IOException {
        PromptSpy prompt = new PromptSpy(new StringReader("a\nb\n2\n"));
        HumanPlayer player = new HumanPlayer(prompt, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
    }

    @Test
    public void playerRepromptedUntilBoardValidatesMoveAsValid() throws IOException {
        PromptSpy promptSpy = new PromptSpy(new StringReader("0\n0\n10\n-1\n2\n"));
        HumanPlayer player = new HumanPlayer(promptSpy, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        assertThat(player.chooseNextMoveFrom(board), is(2));
        assertThat(promptSpy.getNumberOfTimesPlayerIsPromptedForTheirMove(), is(5));
    }

    @Test
    public void playerAskedForTheirNextMove() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("2"));
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);
        HumanPlayer player = new HumanPlayer(promptSpy, X);

        player.chooseNextMoveFrom(board);

        assertThat(promptSpy.getNumberOfTimesPlayerIsPromptedForTheirMove(), is(1));
    }

    @Test
    public void boardPrintedWhenPlayerChoosesMove() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("2"));
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);
        HumanPlayer player = new HumanPlayer(promptSpy, X);

        player.chooseNextMoveFrom(board);

        assertThat(promptSpy.getLastBoardThatWasPrinted(), is("XVACANTXOXOXOVACANT"));
    }

    @Test
    public void boardReprintedWithEveryUserReprompt() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("0\n0\n10\n-1\n2\n"));
        HumanPlayer player = new HumanPlayer(promptSpy, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        player.chooseNextMoveFrom(board);

        assertThat(promptSpy.getNumberOfTimesBoardIsPrinted(), is(5));
    }

    @Test
    public void promptClearsScreenEachTimeUserIsPrompted() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("0\n0\n10\n-1\n2\n"));
        HumanPlayer player = new HumanPlayer(promptSpy, X);
        Board board = new Board(X, VACANT, X, O, X, O, X, O, VACANT);

        player.chooseNextMoveFrom(board);

        assertThat(promptSpy.getNumberOfTimesClearIsCalled(), is(5));
    }

}
