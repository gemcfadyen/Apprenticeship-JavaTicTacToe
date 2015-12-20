package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.GuiHumanPlayer;
import ttt.player.Player;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class TicTacToeRulesSpy implements GameRules {
    private Player currentPlayer;
    private Board board;
    private boolean hasInitialisedGame = false;
    private boolean winnerChecked = false;
    private boolean hasGotWinnersSymbol = false;
    private int numberOfTimesBoardObtained = 0;
    private boolean gameIsPlayed = false;
    private boolean gotCurrentPlayer = false;

    public TicTacToeRulesSpy() {
    }

    public TicTacToeRulesSpy(Board board) {
        this.board = board;
    }

    public TicTacToeRulesSpy(Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public PlayerSymbol getWinningSymbol() {
        hasGotWinnersSymbol = true;
        return board.getWinningSymbol();
    }

    @Override
    public boolean hasWinner() {
        winnerChecked = true;
        return board.hasWinningCombination();
    }

    @Override
    public boolean noWinnerYet() {
        return !hasWinner();
    }

    @Override
    public void initialiseGame(GameType gameType, String dimension) {
        if (board == null) {
            board = new Board(Integer.valueOf(dimension));
        }
        hasInitialisedGame = true;
    }

    @Override
    public Board getBoard() {
        numberOfTimesBoardObtained++;
        return board;
    }

    @Override
    public boolean boardHasFreeSpace() {
        return board.hasFreeSpace();
    }

    @Override
    public void playGame() {
        gameIsPlayed = true;
    }

    @Override
    public Player getCurrentPlayer() {
        gotCurrentPlayer = true;
        return currentPlayer == null ? new GuiHumanPlayer(X) : currentPlayer;
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }

    public boolean gameCheckedForWin() {
        return winnerChecked;
    }

    public boolean hasGotWinnersSymbol() {
        return hasGotWinnersSymbol;
    }

    public int numberOfTimesBoardIsObtained() {
        return numberOfTimesBoardObtained;
    }

    public boolean hasGameBeenPlayed() {
        return gameIsPlayed;
    }

    public boolean hasGotCurrentPlayer() {
        return gotCurrentPlayer;
    }
}
