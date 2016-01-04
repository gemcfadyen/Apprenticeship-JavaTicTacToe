package ttt.commandlineapp;

import org.junit.Test;
import ttt.commandlineapp.formatting.PlainBoard;
import ttt.commandlineapp.formatting.StandardTextPresenter;
import ttt.game.*;
import ttt.game.board.Board;
import ttt.game.board.BoardFactory;
import ttt.game.rules.TicTacToeRules;
import ttt.game.GameConfigurationSpy;

import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.GameType.HUMAN_VS_HUMAN;
import static ttt.game.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.game.PlayerSymbol.*;

public class CommandLineGameControllerTest {
    private static final String HUMAN_VS_HUMAN_ID = "1\n";
    private static final String INPUT_FOR_3x3 = "3\n";
    private static final String DO_NOT_REPLAY = "N\n";
    private TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy();
    private GameConfigurationSpy gameConfigurationSpy = new GameConfigurationSpy();

    @Test
    public void getGameType() {
        PromptSpy promptSpy = createPromptSpyToReadInput(HUMAN_VS_HUMAN_ID);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                gameRulesSpy,
                promptSpy
        );

        commandLineGameController.getGameTypeFromPlayer();

        assertThat(gameConfigurationSpy.hasObtainedGameTypes(), is(true));
        assertThat(promptSpy.getNumberOfTimesPromptedForGameOption(), is(1));
        assertThat(commandLineGameController.getGameType(), is(HUMAN_VS_HUMAN));
        assertThat(promptSpy.getNumberOfTimesGameOptionsWereRead(), is(1));
    }

    @Test
    public void getBoardDimensionsForGameType() {
        PromptSpy promptSpy = createPromptSpyToReadInput(INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                gameRulesSpy,
                promptSpy
        );

        int dimension = commandLineGameController.getDimensionChoiceFromPlayer(HUMAN_VS_UNBEATABLE);

        assertThat(promptSpy.getNumberOfTimesDimensionsHaveBeenAskedFor(), is(1));
        assertThat(promptSpy.getNumberOfTimesBoardDimensionRead(), is(1));
        assertThat(dimension, is(3));
    }

    @Test
    public void gameIsWonWhenPlayerPlacesWinningMove() {
        PromptSpy gamePrompt = new PromptSpy(new StringReader(""));
        Board finalBoard = boardWith(
                X, X, X,
                O, X, O,
                O, X, O);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                new TicTacToeRulesSpy(finalBoard),
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
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                gameRulesSpy,
                gamePrompt
        );

        commandLineGameController.printExitMessage();

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
        TicTacToeRulesSpy gameRulesSpy = new TicTacToeRulesSpy(board);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                gameRulesSpy,
                gamePrompt
        );

        commandLineGameController.printExitMessage();

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
        TicTacToeRulesSpy gameRules = new TicTacToeRulesSpy(board);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                gameRules,
                gamePrompt
        );

        commandLineGameController.startGame();

        assertThat(gamePrompt.getNumberOfTimesXHasWon(), is(1));
        assertThat(gamePrompt.getNumberOfTimesReplayPresented(), is(1));
        assertThat(gamePrompt.getNumberOfTimesReplayOptionRead(), is(1));
    }

    @Test
    public void playersTakeTurnsUntilTheBoardIsFull() {
        PlayerSpy player1Spy = new PlayerSpy(X, createCommandPromptToReadInput("1\n5\n6\n7\n8\n"));
        PlayerSpy player2Spy = new PlayerSpy(O, createCommandPromptToReadInput("2\n3\n4\n9\n"));
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameConfigurationSpy,
                createTicTacToeRules(player1Spy, player2Spy),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN_ID + INPUT_FOR_3x3 + DO_NOT_REPLAY)
        );

        commandLineGameController.startGame();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
    }

    private Board boardWith(PlayerSymbol... layout) {
        return new Board(layout);
    }

    private Prompt createCommandPromptToReadInput(String usersInputs) {
        StandardTextPresenter textPresenter = new StandardTextPresenter();
        return new CommandPrompt(new StringReader(usersInputs), new StringWriter(), new PlainBoard(), textPresenter);
    }

    private PromptSpy createPromptSpyToReadInput(String usersInput) {
        return new PromptSpy(new StringReader(usersInput));
    }

    private TicTacToeRules createTicTacToeRules(PlayerSpy player1Spy, PlayerSpy player2Spy) {
        return new TicTacToeRules(new BoardFactory(), new CommandLinePlayerFactoryStub(player1Spy, player2Spy));
    }
}