package ttt.gui;

import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.CommandLinePlayerFactoryStub;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.CommandLinePlayerFactory;
import ttt.player.FakePlayer;
import ttt.player.GuiHumanPlayer;
import ttt.player.Player;
import ttt.ui.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.player.PlayerSymbol.*;

public class TicTacToeRulesTest {
    private static final Prompt UNUSED_PROMPT = null;
    private Board board = new Board(3);
    private Player[] players = new CommandLinePlayerFactory(UNUSED_PROMPT).createPlayers(HUMAN_VS_HUMAN, 3);


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

        gamesRules.initialiseGame(HUMAN_VS_HUMAN, 3);

        assertThat(playerFactorySpy.getGameTypeUsed(), is(HUMAN_VS_HUMAN));
    }

    @Test
    public void initialisesGame() {
        TicTacToeRules ticTacToeRules = initialiseRulesWithFactories(
                new BoardFactoryStub(board),
                new CommandLinePlayerFactoryStub(players)
        );

        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, 3);

        assertThat(ticTacToeRules.getBoard(), is(board));
    }

    @Test
    public void currentPlayerReinitialisedWhenNewGameStarted() {
        TicTacToeRules ticTacToeRules = initialiseRulesWithFactories(
                new BoardFactoryStub(board, board),
                new CommandLinePlayerFactoryStub(new FakePlayer(X, 0, 1, 2), new FakePlayer(O, 3, 4))
        );

        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, 3);
        ticTacToeRules.playGame();
        ticTacToeRules.initialiseGame(HUMAN_VS_UNBEATABLE, 3);

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

        ticTacToeRules.initialiseGame(HUMAN_VS_HUMAN, 3);

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
    public void gameLoopsUntilThereIsAWinner() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                board,
                new Player[]{new FakePlayer(X, 0, 1, 2), new FakePlayer(O, 3, 4)}
        );

        ticTacToeRules.playGame();

        assertThat(board.getSymbolAt(0), is(X));
        assertThat(board.getSymbolAt(3), is(O));
        assertThat(board.getSymbolAt(1), is(X));
        assertThat(board.getSymbolAt(4), is(O));
        assertThat(board.getSymbolAt(2), is(X));
        assertThat(ticTacToeRules.hasWinner(), is(true));
    }

    @Test
    public void gameLoopsUntilNoSpaceOnBoard() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                board,
                new Player[]{new FakePlayer(X, 0, 2, 4, 5, 7), new FakePlayer(O, 1, 3, 6, 8)}
        );

        ticTacToeRules.playGame();

        assertThat(board.getSymbolAt(0), is(X));
        assertThat(board.getSymbolAt(1), is(O));
        assertThat(board.getSymbolAt(2), is(X));
        assertThat(board.getSymbolAt(3), is(O));
        assertThat(board.getSymbolAt(4), is(X));
        assertThat(board.getSymbolAt(6), is(O));
        assertThat(board.getSymbolAt(5), is(X));
        assertThat(board.getSymbolAt(8), is(O));
        assertThat(board.getSymbolAt(7), is(X));
        assertThat(ticTacToeRules.hasAvailableMoves(), is(false));
    }

    @Test
    public void gameLoopsUntilPlayerIsNotReady() {
        GuiHumanPlayer testPlayer = new GuiHumanPlayer(X);
        testPlayer.update(0);
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                board,
                new Player[]{testPlayer, new FakePlayer(O, 3, 7)}
        );

        ticTacToeRules.playGame();

        assertThat(board.getSymbolAt(0), is(X));
        assertThat(board.getSymbolAt(3), is(O));
        assertThat(board.getSymbolAt(7), is(VACANT));
    }

    @Test
    public void getCurrentPlayer() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                board,
                new Player[]{new GuiHumanPlayer(X), new FakePlayer(O)}
        );

        Player currentPlayer = ticTacToeRules.getCurrentPlayer();

        assertThat(currentPlayer, is(instanceOf(GuiHumanPlayer.class)));
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
}

