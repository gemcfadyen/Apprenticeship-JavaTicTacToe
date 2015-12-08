package ttt.gui;

import org.hamcrest.Matchers;
import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.GameType;
import ttt.PlayerFactoryStub;
import ttt.PromptSpy;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.io.StringReader;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ttt.GameType.*;
import static ttt.player.PlayerSymbol.*;

public class TicTacToeRulesTest {
    private static final Prompt UNUSED_PROMPT = null;
    private Player[] players = new PlayerFactory(UNUSED_PROMPT).createPlayers(HUMAN_VS_HUMAN, 3);
    private Board board = new Board(3);

    @Test
    public void makesMove() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);

        ticTacToeRules.playMoveAt("1");

        assertThat(board.getSymbolAt(1), is(X));
    }

    @Test
    public void currentPlayersSymbol() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);

        PlayerSymbol currentPlayerSymbol = ticTacToeRules.getCurrentPlayerSymbol();

        assertThat(currentPlayerSymbol, is(X));
    }

    @Test
    public void hasWinner() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        VACANT, VACANT, X
                ),
                players);

        assertThat(ticTacToeRules.hasWinner(), is(true));
    }

    @Test
    public void hasNoWinner() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                new Board(
                        X, O, X,
                        O, O, X,
                        VACANT, VACANT, VACANT
                ),
                players
        );
        assertThat(ticTacToeRules.hasWinner(), is(false));
    }

    @Test
    public void togglesPlayer() {
        TicTacToeRules gamesRules = new TicTacToeRules(board, players);

        PlayerSymbol currentPlayer = gamesRules.getCurrentPlayerSymbol();
        gamesRules.togglePlayer();
        PlayerSymbol toggledPlayer = gamesRules.getCurrentPlayerSymbol();

        assertThat(currentPlayer, is(not(equalTo(toggledPlayer))));
    }

    @Test
    public void getGameTypes() {
        TicTacToeRules gamesRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(UNUSED_PROMPT));

        List<GameType> gameTypes = gamesRules.getGameTypes();

        assertThat(gameTypes, Matchers.contains(HUMAN_VS_HUMAN, HUMAN_VS_UNBEATABLE, UNBEATABLE_VS_HUMAN));
    }

    @Test
    public void getDimensions() {
        TicTacToeRules gamesRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory(UNUSED_PROMPT));

        String dimension = gamesRules.getDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is("5"));
    }

    @Test
    public void gameTypeIsSet() {
        PlayerFactorySpy playerFactorySpy = new PlayerFactorySpy();
        TicTacToeRules gamesRules = new TicTacToeRules(new BoardFactory(), playerFactorySpy);

        gamesRules.storeGameType(HUMAN_VS_HUMAN);
        gamesRules.initialiseGame("3");

        assertThat(playerFactorySpy.getGameTypeUsed(), is(HUMAN_VS_HUMAN));
    }

    @Test
    public void initialisesGame() {
        Board board = new Board(3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                new BoardFactoryStub(board),
                new PlayerFactoryStub(players)
        );
        ticTacToeRules.initialiseGame("3");
        assertThat(ticTacToeRules.getBoard(), is(board));
    }

    @Test
    public void getBoard() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                VACANT, VACANT, X
        );

        TicTacToeRules ticTacToeRules = new TicTacToeRules(new BoardFactoryStub(board), new PlayerFactory(UNUSED_PROMPT));
        ticTacToeRules.initialiseGame("3");
        assertThat(ticTacToeRules.getBoard(), is(board));
    }

    @Test
    public void boardHasFreeSpace() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        VACANT, VACANT, X
                ),
                players
        );

        assertThat(ticTacToeRules.boardHasFreeSpace(), is(true));
    }

    @Test
    public void boardHasNoFreeSpace() {
        TicTacToeRules ticTacToeRules = new TicTacToeRules(
                new Board(
                        X, O, X,
                        O, X, O,
                        X, O, X),
                players
        );

        assertThat(ticTacToeRules.boardHasFreeSpace(), is(false));
    }

    @Test
    public void getCurrentPlayersNextMove() {
        PromptSpy promptSpy = new PromptSpy(new StringReader("1"));
        players = new PlayerFactory(promptSpy).createPlayers(HUMAN_VS_HUMAN, 3);
        TicTacToeRules gamesRules = new TicTacToeRules(board, players);

        String move = gamesRules.getCurrentPlayersNextMove();

        assertThat(move, is("1"));
    }
}

