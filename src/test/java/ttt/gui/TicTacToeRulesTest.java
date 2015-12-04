package ttt.gui;

import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.GameType;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class TicTacToeRulesTest {
    private static final Prompt UNUSED_PROMPT = null;

    @Test
    public void makesMove() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        ticTacToeRules.playMoveAt("1");

        assertThat(board.getSymbolAt(1), is(X));
    }

    @Test
    public void currentPlayersSymbol() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        PlayerSymbol currentPlayerSymbol = ticTacToeRules.getCurrentPlayer();

        assertThat(currentPlayerSymbol, is(X));
    }

    @Test
    public void hasWinner() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                VACANT, VACANT, X
        );

        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        assertThat(ticTacToeRules.hasWinner(), is(true));
    }

    @Test
    public void hasNoWinner() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                VACANT, VACANT, VACANT
        );

        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        assertThat(ticTacToeRules.hasWinner(), is(false));
    }

    @Test
    public void hasFreeSpace() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        assertThat(ticTacToeRules.hasFreeSpace(), is(true));
    }

    @Test
    public void noFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                X, X, O);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        TicTacToeRules ticTacToeRules = new TicTacToeRules(board, players);
        assertThat(ticTacToeRules.hasFreeSpace(), is(false));
    }

    @Test
    public void togglesPlayer() {
        TicTacToeRules gamesRules = new TicTacToeRules(new Board(3), new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, null, 3));

        PlayerSymbol currentPlayer = gamesRules.getCurrentPlayer();
        gamesRules.togglePlayer();
        PlayerSymbol toggledPlayer = gamesRules.getCurrentPlayer();

        assertThat(currentPlayer, is(not(equalTo(toggledPlayer))));
    }

    @Test
    public void getGameTypes() {
        TicTacToeRules gamesRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory());

        GameType gameType = gamesRules.getGameTypes();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }

    @Test
    public void getDimensions() {
        TicTacToeRules gamesRules = new TicTacToeRules(new BoardFactory(), new PlayerFactory());

        String dimension = gamesRules.getDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is("5"));
    }

    @Test
    public void getBoard() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                VACANT, VACANT, X
        );

        TicTacToeRules ticTacToeRules = new TicTacToeRules(new BoardFactoryStub(board), new PlayerFactory());
        ticTacToeRules.initialiseGame("3");
        assertThat(ticTacToeRules.getBoard(), is(board));
    }
}

