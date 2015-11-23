package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.*;
import ttt.ui.CommandPrompt;
import ttt.ui.Prompt;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.player.PlayerSymbol.*;

public class GameTest {
    private static final int DIMENSION = 3;
    private static final String HUMAN_VS_UNBEATABLE_FOR_PROMPT = "2\n";
    private static final String DIMENSION_FOR_PROMPT = "3\n";
    private static final String HUMAN_VS_HUMAN = "1\n";
    private static final String DO_NOT_REPLAY = "N\n";

    @Test
    public void createsBoardOfSpecifiedDimension() {
        Prompt gamePrompt = new CommandPrompt(
                new StringReader(String.valueOf(DIMENSION)),
                new StringWriter());

        BoardFactoryStub boardFactoryStub = new BoardFactoryStub();
        Game game = new Game(
                boardFactoryStub,
                gamePrompt,
                new PlayerFactory()
        );

        assertThat(game.getBoardOfCorrectDimensionFor(HUMAN_VS_UNBEATABLE), is(DIMENSION));
        assertThat(boardFactoryStub.getLatestBoard().getRows().size(), is(3));
    }

    @Test
    public void setsUpPlayersForSpecifiedGameType() {
        Prompt gamePrompt = new CommandPrompt(
                new StringReader(HUMAN_VS_UNBEATABLE_FOR_PROMPT + DIMENSION_FOR_PROMPT),
                new StringWriter());

        Game game = new Game(
                new BoardFactory(),
                gamePrompt,
                new PlayerFactory()
        );

        Player[] players = game.setupPlayers();

        assertThat(players.length, is(2));
        assertThat(players[0], instanceOf(HumanPlayer.class));
        assertThat(players[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void gameIsOverWhenBoardIsFull() {
        Board board = new Board(
                X, O, X,
                X, X, O,
                O, X, O);
        Game game = new Game(board, new CommandPrompt(new StringReader(""), new StringWriter()), new PlayerFactory());

        boolean isGameInProgress = game.gameInProgress(false);

        assertThat(isGameInProgress, is(false));
    }

    @Test
    public void gameIsWonWhenPlayerPlacesWinningMove() {
        Board board = new Board(
                X, VACANT, X,
                O, X, O,
                O, X, O);
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        Game game = new Game(board, gamePrompt, new PlayerFactory());
        CommandPrompt promptWithWinningMove = new CommandPrompt(new StringReader("2\n"), new StringWriter());
        Player[] players = new Player[]{
                new HumanPlayer(X, promptWithWinningMove),
                new HumanPlayer(O, new CommandPrompt(new StringReader(""), new StringWriter()))};

        game.playSingleGame(players);

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
    }

    @Test
    public void boardIsUpdatedWithPlayersMove() {
        Board board = new Board(DIMENSION);
        Game game = new Game(board,
                new CommandPrompt(new StringReader(""), new StringWriter()),
                new PlayerFactory());
        CommandPrompt playersMove = new CommandPrompt(new StringReader("2\n"), new StringWriter());

        PlayerSpy humanPlayer = new PlayerSpy(X, playersMove);

        game.updateBoardWithPlayersMove(humanPlayer);

        assertThat(board.getSymbolAt(1), is(X));
        assertThat(board.getVacantPositions().size(), is(8));
    }

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n"));
        PlayerSpy player2Spy = new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n"));
        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                player1Spy,
                player2Spy
        );

        Game game = new Game(new BoardFactory(),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN + DIMENSION_FOR_PROMPT + DO_NOT_REPLAY),
                playerFactoryStub);

        game.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    @Test
    public void printsCongratulatoryMessageWhenThereIsAWin() {
        Board boardWithWinningRow = new Board(
                X, X, X,
                O, VACANT, VACANT,
                O, VACANT, VACANT);
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        Game game = new Game(boardWithWinningRow, gamePrompt, null);

        game.displayResultsOfGame(true);

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOVACANTVACANTOVACANTVACANT"));
    }

    @Test
    public void printsDrawMessageWhenThereIsNoWinOrFreeSpaces() {
        Board boardWithWinningRow = new Board(
                X, O, X,
                O, O, X,
                O, X, O);
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        Game game = new Game(boardWithWinningRow, gamePrompt, null);

        game.displayResultsOfGame(false);

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XOXOOXOXO"));
    }

//    @Test
//    public void printsDrawMessageWhenThereAreNoMoreSpacesOnTheBoardAndNoWinner() {
//        Board board = new Board(X, O, O, O, X, X, VACANT, VACANT, O);
//        PromptSpy gamePrompt = new PromptSpy(new StringReader(HUMAN_VS_HUMAN + DIMENSION_FOR_PROMPT + DO_NOT_REPLAY));

    @Test
    public void promptsUserToPlayAgainAtTheEndOfGame() {
        Board board = new Board(X, VACANT, X, O, X, O, O, O, X);
        PromptSpy gamePrompt = new PromptSpy(
                new StringReader(HUMAN_VS_HUMAN
                        + DIMENSION_FOR_PROMPT
                        + DO_NOT_REPLAY));

        PlayerFactoryStub playerFactoryStub = new PlayerFactoryStub(
                createHumanPlayer(X, createCommandPromptToReadInput("2\n")),
                createHumanPlayer(O, createCommandPromptToReadInput(""))
        );

        Game game = new Game(new BoardFactoryStub(board, new Board(3)), gamePrompt, playerFactoryStub);

        game.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(1));
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private HumanPlayer createHumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}