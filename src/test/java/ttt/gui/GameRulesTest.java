package ttt.gui;

import org.hamcrest.Matchers;
import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;

public class GameRulesTest {
    @Test
    public void getCurrentPlayer() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        assertThat(gamesRules.getCurrentPlayer(), is(X));
    }

    @Test
    public void playerMakesMove() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        gamesRules.playMoveAt("7");

        assertThat(board.getSymbolAt(7), is(X));
    }

    @Test
    public void gameHasWinner() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, X);
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        boolean hasWinner = gameRules.hasWinner();

        assertThat(hasWinner, is(true));
    }

    @Test
    public void boardHasFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, VACANT);
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        boolean hasFreeSpace = gameRules.boardHasFreeSpace();

        assertThat(hasFreeSpace, is(true));
    }

    @Test
    public void boardHasNoFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, VACANT);
        GameRules gameRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        boolean hasWinner = gameRules.hasWinner();

        assertThat(hasWinner, is(false));
    }

    @Test
    public void togglesPlayer() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(board, new PlayerFactory().createPlayers(GameType.HUMAN_VS_HUMAN, null, 3));

        PlayerSymbol currentPlayer = gamesRules.getCurrentPlayer();
        gamesRules.togglePlayer();
        PlayerSymbol toggledPlayer = gamesRules.getCurrentPlayer();

        assertThat(currentPlayer, is(Matchers.not(Matchers.equalTo(toggledPlayer))));
    }
}

