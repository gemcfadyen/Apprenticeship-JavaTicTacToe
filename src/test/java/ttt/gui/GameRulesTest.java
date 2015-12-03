package ttt.gui;

import org.hamcrest.Matchers;
import org.junit.Test;
import ttt.BoardFactoryStub;
import ttt.board.Board;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.player.PlayerSymbol.*;

public class GameRulesTest {
    @Test
    public void getCurrentPlayer() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(
                new GuiPromptSpy(),
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3
        );

        assertThat(gamesRules.getCurrentPlayer(), is(X));
    }

    @Test
    public void playerMakesMove() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(
                new GuiPromptSpy(),
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3
        );

        gamesRules.playMoveAt("7");

        assertThat(board.getSymbolAt(7), is(X));
//        assertThat(gamesRules.getCurrentPlayer(), is(O));
    }

//    @Test
//    public void identifiesWin() {
//        Board board = new Board(
//                VACANT, X, X,
//                O, O, VACANT,
//                VACANT, VACANT, VACANT);
//        BoardFactory boardFactory = new BoardFactoryStub(board);
//        GuiPromptSpy playerPrompt = new GuiPromptSpy();
//        GameRules gameRules = new GameRules(
//                playerPrompt,
//                new PlayerFactory(),
//                boardFactory,
//                HUMAN_VS_HUMAN,
//                3);
//
//        gameRules.playMoveAt("0");
//
//        assertThat(playerPrompt.numberOfTimesWinPrinted(), is(1));
//    }

//    @Test
//    public void identifiesDraw() {
//        Board board = new Board(
//                X, O, X,
//                O, O, X,
//                X, VACANT, O);
//        GuiPromptSpy playerPrompt = new GuiPromptSpy();
//        GameRules gameRules = new GameRules(playerPrompt,
//                new PlayerFactory(),
//                new BoardFactoryStub(board),
//                HUMAN_VS_HUMAN,
//                3);
//
//        gameRules.playMoveAt("7");
//
//        assertThat(playerPrompt.numberOfTimesDrawPrinted(), is(1));
//    }

//    @Test
//    public void whenLastMoveCreatesWinningRowThenWinningMessageReported() {
//        Board board = new Board(
//                X, O, X,
//                O, O, X,
//                O, X, VACANT);
//        GuiPromptSpy playerPrompt = new GuiPromptSpy();
//        GameRules gameRules = new GameRules(playerPrompt,
//                new PlayerFactory(),
//                new BoardFactoryStub(board),
//                HUMAN_VS_HUMAN,
//                3);
//
//        gameRules.playMoveAt("8");
//
//        assertThat(playerPrompt.numberOfTimesWinPrinted(), is(1));
//        assertThat(playerPrompt.numberOfTimesDrawPrinted(), is(0));
//    }

    @Test
    public void gameHasWinner() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, X);
        GuiPromptSpy playerPrompt = new GuiPromptSpy();
        GameRules gameRules = new GameRules(playerPrompt,
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3);

        boolean hasWinner = gameRules.hasWinner();

        assertThat(hasWinner, is(true));
    }

    @Test
    public void boardHasFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, VACANT);
        GuiPromptSpy playerPrompt = new GuiPromptSpy();
        GameRules gameRules = new GameRules(playerPrompt,
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3);

        boolean hasFreeSpace = gameRules.boardHasFreeSpace();

        assertThat(hasFreeSpace, is(true));
    }

    @Test
    public void boardHasNoFreeSpace() {
        Board board = new Board(
                X, O, X,
                O, O, X,
                O, X, VACANT);
        GuiPromptSpy playerPrompt = new GuiPromptSpy();
        GameRules gameRules = new GameRules(playerPrompt,
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3);

        boolean hasWinner = gameRules.hasWinner();

        assertThat(hasWinner, is(false));
    }

    @Test
    public void togglesPlayer() {
        Board board = new Board(3);
        GameRules gamesRules = new GameRules(
                new GuiPromptSpy(),
                new PlayerFactory(),
                new BoardFactoryStub(board),
                HUMAN_VS_HUMAN,
                3
        );

        PlayerSymbol currentPlayer = gamesRules.getCurrentPlayer();
        gamesRules.togglePlayer();
        PlayerSymbol toggledPlayer = gamesRules.getCurrentPlayer();

        assertThat(currentPlayer, is(Matchers.not(Matchers.equalTo(toggledPlayer))));
    }
}

