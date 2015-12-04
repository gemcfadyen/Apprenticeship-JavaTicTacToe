package ttt.gui;

import org.junit.Test;
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

public class GameRulesTest {
    private static final Prompt UNUSED_PROMPT = null;

    @Test
    public void makesMove() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        GameRules gameRules = new GameRules(board, players);
        gameRules.playMoveAt("1");

        assertThat(board.getSymbolAt(1), is(X));
    }

    @Test
    public void currentPlayersSymbol() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        GameRules gameRules = new GameRules(board, players);
        PlayerSymbol currentPlayerSymbol = gameRules.getCurrentPlayer();

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

        GameRules gameRules = new GameRules(board, players);
        assertThat(gameRules.hasWinner(), is(true));
    }

    @Test
    public void hasNoWinner() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                VACANT, VACANT, VACANT
        );

        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        GameRules gameRules = new GameRules(board, players);
        assertThat(gameRules.hasWinner(), is(false));
    }

    @Test
    public void hasFreeSpace() {
        Board board = new Board(3);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        GameRules gameRules = new GameRules(board, players);
        assertThat(gameRules.hasFreeSpace(), is(true));
    }

    @Test
    public void noFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, X, O,
                X, X, O);
        Player[] players = new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, UNUSED_PROMPT, 3);

        GameRules gameRules = new GameRules(board, players);
        assertThat(gameRules.hasFreeSpace(), is(false));
    }

    @Test
    public void togglesPlayer() {
        GameRules gamesRules = new GameRules(new Board(3), new PlayerFactory().createPlayers(HUMAN_VS_HUMAN, null, 3));

        PlayerSymbol currentPlayer = gamesRules.getCurrentPlayer();
        gamesRules.togglePlayer();
        PlayerSymbol toggledPlayer = gamesRules.getCurrentPlayer();

        assertThat(currentPlayer, is(not(equalTo(toggledPlayer))));
    }

    @Test
    public void getGameTypes() {
        GameRules gamesRules = new GameRules(new BoardFactory(), new PlayerFactory());

        GameType gameType = gamesRules.getGameTypes();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }


}
