package ttt;

import org.junit.Test;
import ttt.board.Board;
import ttt.gui.TicTacToeRulesSpy;
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
    public void setsUpPlayersForGameType() {
        PromptSpy promptSpy = createPromptSpyToReadInput(HUMAN_VS_UNBEATABLE_OPTION + INPUT_FOR_3x3);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
                new Board(3),
                promptSpy,
                new PlayerFactory()
        );

        Player[] players = commandLineGameController.setupPlayers(GameType.HUMAN_VS_HUMAN, 3);

        assertThat(players.length, is(2));
        assertThat(players[0], instanceOf(HumanPlayer.class));
        assertThat(players[1], instanceOf(HumanPlayer.class));
    }

    @Test
    public void gameIsOverWhenBoardIsFull() {
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRulesSpy,
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
                gameRulesSpy,
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
                gameRulesSpy,
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
                gameRulesSpy,
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
        TicTacToeRulesSpy gameRules = new TicTacToeRulesSpy(board);
        CommandLineGameController commandLineGameController = new CommandLineGameController(
                gameRules,
                board,
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
                gameRulesSpy,
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
                gameRulesSpy,
                new Board(3),
                createCommandPromptToReadInput(HUMAN_VS_HUMAN_ID + INPUT_FOR_3x3 + DO_NOT_REPLAY),
                new PlayerFactoryStub(player1Spy, player2Spy)
        );

        commandLineGameController.play();

        assertThat(player1Spy.numberOfTurnsTaken(), is(5));
        assertThat(player2Spy.numberOfTurnsTaken(), is(4));
        assertThat(gameRulesSpy.hasInitialisedGame(), is(true));
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