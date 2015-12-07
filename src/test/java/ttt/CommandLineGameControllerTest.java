package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.gui.TicTacToeRules;
import ttt.gui.TicTacToeRulesSpy;
import ttt.player.*;
import ttt.ui.CommandPrompt;
import ttt.ui.PlainFormatter;
import ttt.ui.Prompt;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.player.PlayerSymbol.*;

public class CommandLineGameControllerTest {
    private static final String HUMAN_VS_HUMAN_ID = "1\n";
    private static final String INPUT_FOR_3x3 = "3\n";
    private static final String DO_NOT_REPLAY = "N\n";
    private TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();


    @Test
    public void gameTypePresented() {
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                promptSpy
        );

        commandLineGameController.presentGameTypes();

        assertThat(gameRulesSpy.hasObtainedGameTypes(), is(true));
        assertThat(promptSpy.getNumberOfTimesPromptedForGameOption(), is(1));
    }

    @Test
    public void gameTypeRead() {
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                promptSpy
        );

        commandLineGameController.readGameType();

        assertThat(gameRulesSpy.hasStoredGameType(), is(true));
        assertThat(promptSpy.getNumberOfTimesGameOptionsWereRead(), is(1));
    }

    @Test
    public void presentsBoardDimensionsForGameType() {
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                promptSpy
        );

        commandLineGameController.presentBoardDimensionsFor(HUMAN_VS_UNBEATABLE);

        assertThat(promptSpy.getNumberOfTimesDimensionsHaveBeenAskedFor(), is(1));
        assertThat(gameRulesSpy.hasObtainedBoardDimensions(), is(true));
    }

    @Test
    public void readsBoardDimension() {
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                promptSpy
        );

        int dimension = commandLineGameController.readDimension(4);

        assertThat(promptSpy.getNumberOfTimesBoardDimensionRead(), is(1));
        assertThat(dimension, is(3));
    }

    @Test
    public void gameIsOverWhenBoardIsFull() {
        Board board = boardWith(
                X, O, X,
                X, X, O,
                O, O, X
        );
        gameRulesSpy = new TicTacToeRulesSpy(board, "");

        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                commandPrompt()
        );

        boolean gameInProgress = commandLineGameController.gameInProgress();

        assertThat(gameInProgress, is(false));
        assertThat(gameRulesSpy.boardCheckedForFreeSpace(), is(true));
    }

    @Test
    public void gameIsWonWhenPlayerPlacesWinningMove() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));

        Board board = boardWith(
                X, VACANT, X,
                O, X, O,
                O, X, O);
        gameRulesSpy = new TicTacToeRulesSpy(board, "1");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                gamePrompt
        );

        commandLineGameController.playMatch();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
    }

    @Test
    public void printsCongratulatoryMessageAndBoardWhenThereIsAWin() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        Board board = boardWith(
                X, X, X,
                O, VACANT, VACANT,
                O, VACANT, VACANT);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board, "");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                gamePrompt
        );

        commandLineGameController.displayResultsOfGame();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesOHasWon(), is(0));
        assertThat(gamePrompt.getLastBoardThatWasPrinted(), is("XXXOVACANTVACANTOVACANTVACANT"));
        assertThat(gameRulesSpy.hasGotWinnersSymbol(), is(true));
    }

    @Test
    public void printsDrawMessageAndBoardWhenThereIsADraw() {
        PromptSpy gamePrompt = createPromptSpyToReadInput("");
        Board board = boardWith(
                X, O, X,
                O, O, X,
                O, X, O);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board, "");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                gamePrompt
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
        TicTacToeRulesSpy gameRules = new TicTacToeRulesSpy(board, "1");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                gamePrompt
        );

        commandLineGameController.startGame();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesPlayerIsPromptedToPlayAgain(), is(1));
    }

    @Test
    public void boardIsUpdatedWithPlayersMove() {
        Board board = new Board(3);
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board, "1");
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                commandPrompt()
        );

        commandLineGameController.updateBoardWithPlayersMove();

        assertThat(board.getSymbolAt(1), is(X));
        assertThat(board.getVacantPositions().size(), is(8));
    }

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n"));
        PlayerSpy player2Spy = new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n"));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                new TicTacToeRules(new BoardFactory(), new PlayerFactoryStub(player1Spy, player2Spy)),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN_ID + INPUT_FOR_3x3 + DO_NOT_REPLAY)
        );

        commandLineGameController.startGame();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    private Board boardWith(PlayerSymbol... layout) {
        return new Board(layout);
    }

    private CommandPrompt commandPrompt() {
        return new CommandPrompt(new StringReader(""), new StringWriter(), new PlainFormatter());
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter(), new PlainFormatter());
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