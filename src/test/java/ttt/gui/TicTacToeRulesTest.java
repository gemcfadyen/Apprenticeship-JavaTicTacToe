package ttt.gui;

import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.CommandLinePlayerFactoryStub;
import ttt.PromptSpy;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.CommandLinePlayerFactory;
import ttt.player.Player;
import ttt.ui.Prompt;

import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.player.PlayerSymbol.*;

public class TicTacToeRulesTest {
    private static final Prompt UNUSED_PROMPT = null;
    private Board board = new Board(3);
    private Player[] players = new CommandLinePlayerFactory(UNUSED_PROMPT).createPlayers(HUMAN_VS_HUMAN, 3);

    @Test
    public void makesMove() {
        TicTacToeRules ticTacToeRules = initialiseRules(board, players);
        int firstPlayer = ticTacToeRules.getCurrentPlayerIndex();
        ticTacToeRules.takeTurn(1);

        assertThat(board.getSymbolAt(1), is(X));
        assertThat(firstPlayer, is(not(ticTacToeRules.getCurrentPlayerIndex())));
    }

    @Test
    public void hasWinner() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        VACANT, VACANT, X
                ),
                players);

        assertThat(ticTacToeRules.hasWinner(), is(true));
        assertThat(ticTacToeRules.noWinnerYet(), is(false));
    }

    @Test
    public void hasNoWinner() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        X, O, X,
                        O, O, X,
                        VACANT, VACANT, VACANT
                ),
                players
        );
        assertThat(ticTacToeRules.noWinnerYet(), is(true));
        assertThat(ticTacToeRules.hasWinner(), is(false));
    }

    @Test
    public void gameTypeIsSet() {
        CommandLinePlayerFactorySpy playerFactorySpy = new CommandLinePlayerFactorySpy();
        TicTacToeRules gamesRules = initialiseRulesWithFactories(new BoardFactory(), playerFactorySpy);

        gamesRules.initialiseGame(HUMAN_VS_HUMAN, "3");

        assertThat(playerFactorySpy.getGameTypeUsed(), is(HUMAN_VS_HUMAN));
    }

    @Test
    public void initialisesGame() {
        TicTacToeRules ticTacToeRules = initialiseRulesWithFactories(
                new BoardFactoryStub(board),
                new CommandLinePlayerFactoryStub(players)
        );

        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, "3");

        assertThat(ticTacToeRules.getBoard(), is(board));
    }

    @Test
    public void currentPlayerReinitialisedWhenNewGameStarted() {
        TicTacToeRules ticTacToeRules = initialiseRulesWithFactories(
                new BoardFactoryStub(board, board),
                new CommandLinePlayerFactoryStub(players)
        );

        intialiseGameAndTogglePlayer(ticTacToeRules);
        ticTacToeRules.initialiseGame(HUMAN_VS_UNBEATABLE, "3");

        assertThat(ticTacToeRules.getCurrentPlayerIndex(), is(0));
    }

    @Test
    public void getBoard() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                VACANT, VACANT, X
        );
        TicTacToeRules ticTacToeRules = initialiseRulesWithFactories(
                new BoardFactoryStub(board),
                new CommandLinePlayerFactory(UNUSED_PROMPT)
        );

        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, "3");

        assertThat(ticTacToeRules.getBoard(), is(board));
    }

    @Test
    public void gameInProgressWhenBoardHasFreeSpace() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        VACANT, VACANT, VACANT
                ),
                players
        );

        assertThat(ticTacToeRules.gameInProgress(), is(true));
    }

    @Test
    public void gameInProgressWhenBoardHasNoFreeSpace() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        X, O, X),
                players
        );

        assertThat(ticTacToeRules.gameInProgress(), is(false));
    }

    @Test
    public void gameInProgressWhenThereIsNoWinner() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        VACANT, O, X,
                        O, X, O,
                        VACANT, O, X),
                players
        );

        assertThat(ticTacToeRules.gameInProgress(), is(true));
    }

    @Test
    public void gameNotInProgressWhenThereIsAWinner() {
        TicTacToeRules ticTacToeRules = initialiseRules(
                new Board(
                        X, O, X,
                        O, O, X,
                        VACANT, VACANT, X
                ),
                players
        );
        assertThat(ticTacToeRules.gameInProgress(), is(false));
    }

    @Test
    public void getCurrentPlayersNextMove() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("1"));
        TicTacToeRules gamesRules = initialiseRules(
                board,
                new CommandLinePlayerFactory(promptSpy).createPlayers(HUMAN_VS_HUMAN, 3)
        );

        int move = gamesRules.getCurrentPlayersNextMove();

        assertThat(move, is(1));
    }

    private TicTacToeRules initialiseRulesWithFactories(BoardFactory boardFactory, CommandLinePlayerFactory playerFactory) {
        return new TicTacToeRules(
                boardFactory,
                playerFactory
        );
    }

    private TicTacToeRules initialiseRules(Board board, Player[] players) {
        return new TicTacToeRules(
                board,
                players
        );
    }

    private void intialiseGameAndTogglePlayer(TicTacToeRules ticTacToeRules) {
        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, "3");
        ticTacToeRules.takeTurn(1);
    }
}

