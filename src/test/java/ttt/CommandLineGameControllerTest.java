package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.gui.GameRules;
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

public class CommandLineGameControllerTest {
    private static final String HUMAN_VS_UNBEATABLE_OPTION = "2\n";
    private static final String HUMAN_VS_HUMAN_ID = "1\n";
    private static final String INPUT_FOR_3x3 = "3\n";
    private static final String DO_NOT_REPLAY = "N\n";
    private GameRules gameRules;

    @Test
    public void createsBoardOfSpecifiedDimension() {
        BoardFactoryStub boardFactoryStub = new BoardFactoryStub();
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                boardFactoryStub,
                promptSpy,
                new PlayerFactory()
        );

        int dimension = commandLineGameController.getBoardOfCorrectDimensionFor(HUMAN_VS_UNBEATABLE);

        assertThat(promptSpy.getNumberOfTimesDimensionsHaveBeenAskedFor(), is(1));
        assertThat(promptSpy.getNumberOfTimesBoardDimensionRead(), is(1));
        assertThat(dimension, is(3));
        assertThat(boardFactoryStub.getLatestBoard().getRows().size(), is(3));
    }

    @Test
    public void setsUpPlayersForGameType() {
        PromptSpy promptSpy = createPromptSpyToReadInput(HUMAN_VS_UNBEATABLE_OPTION + INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                new BoardFactory(),
                promptSpy,
                new PlayerFactory()
        );

        Player[] players = commandLineGameController.setupPlayers();

        assertThat(promptSpy.getNumberOfTimesPromptedForGameOption(), is(1));
        assertThat(promptSpy.getNumberOfTimesGameOptionsWereRead(), is(1));
        assertThat(players.length, is(2));
        assertThat(players[0], instanceOf(HumanPlayer.class));
        assertThat(players[1], instanceOf(UnbeatablePlayer.class));
    }

    @Test
    public void gameIsOverWhenBoardIsFull() {
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                boardWith(X, O, X,
                        X, X, O,
                        O, X, O),
                commandPrompt(),
                new PlayerFactory()
        );

        assertThat(commandLineGameController.gameInProgress(), is(false));
    }

    @Test
    public void gameIsWonWhenPlayerPlacesWinningMove() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                boardWith(
                        X, VACANT, X,
                        O, X, O,
                        O, X, O),
                gamePrompt,
                new PlayerFactory()
        );

        commandLineGameController.playMatch(twoHumanPlayers());

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
    }

    @Test
    public void printsCongratulatoryMessageAndBoardWhenThereIsAWin() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                boardWith(
                        X, X, X,
                        O, VACANT, VACANT,
                        O, VACANT, VACANT),
                gamePrompt,
                new PlayerFactory()
        );

        commandLineGameController.displayResultsOfGame();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOVACANTVACANTOVACANTVACANT"));
    }

    @Test
    public void printsDrawMessageAndBoardWhenThereIsADraw() {
        PromptSpy gamePrompt = createPromptSpyToReadInput("");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                boardWith(
                        X, O, X,
                        O, O, X,
                        O, X, O),
                gamePrompt,
                new PlayerFactory()
        );

        commandLineGameController.displayResultsOfGame();

        assertThat(gamePrompt.getNumberOfTimesDrawMessageHasBeenPrinted(), is(1));
        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XOXOOXOXO"));
    }

    @Test
    public void promptsUserToPlayAgainAtTheEndOfGame() {
        PromptSpy gamePrompt = createPromptSpyToReadInput(HUMAN_VS_HUMAN_ID + INPUT_FOR_3x3 + DO_NOT_REPLAY);
        Board board = boardWith(
                X, VACANT, X,
                O, X, O,
                O, O, X
        );
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                new BoardFactoryStub(board),
                gamePrompt,
                new PlayerFactoryStub(twoHumanPlayers())
        );

        commandLineGameController.play();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(1));
    }

    @Test
    public void boardIsUpdatedWithPlayersMove() {
        Board board = new Board(3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                board,
                commandPrompt(),
                new PlayerFactory()
        );

        commandLineGameController.updateBoardWithPlayersMove(createHumanPlayer(X, createCommandPromptToReadInput("2\n")));

        assertThat(board.getSymbolAt(1), is(X));
        assertThat(board.getVacantPositions().size(), is(8));
    }

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n"));
        PlayerSpy player2Spy = new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n"));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                new BoardFactory(),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN_ID + INPUT_FOR_3x3 + DO_NOT_REPLAY),
                new PlayerFactoryStub(player1Spy, player2Spy)
        );

        commandLineGameController.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    private Board boardWith(PlayerSymbol... layout) {
        return new Board(layout);
    }

    private CommandPrompt commandPrompt() {
        return new CommandPrompt(new StringReader(""), new StringWriter());
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter());
    }

    private PromptSpy createPromptSpyToReadInput(String usersInput) {
        return new PromptSpy(new StringReader(usersInput));
    }

    private Player[] twoHumanPlayers() {
        return new Player[]{
                createHumanPlayer(X, createCommandPromptToReadInput("2\n")),
                createHumanPlayer(O, commandPrompt())};
    }

    private HumanPlayer createHumanPlayer(PlayerSymbol symbol, Prompt prompt) {
        return new HumanPlayer(symbol, prompt);
    }
}