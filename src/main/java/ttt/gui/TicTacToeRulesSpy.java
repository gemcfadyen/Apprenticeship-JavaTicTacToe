package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class TicTacToeRulesSpy implements GameRules {
    private Board board;
    private boolean hasInitialisedGame = false;
    private boolean hasMadeMove = false;
    private int positionOfMove;
    private boolean winnerChecked = false;
    private boolean boardCheckedForFreeSpaces = false;
    private int nextMove;
    private boolean hasGotWinnersSymbol = false;
    private int numberOfMovesMadeAtSpecificPosition = 0;
    private int numberOfTimesPlayerAskedForMove = 0;
    private int numberOfTimesBoardCheckedForWin = 0;
    private int numberOfTimesBoardObtained = 0;
    private boolean checkedGameIsInProgress = false;

    public TicTacToeRulesSpy() {
    }

    public TicTacToeRulesSpy(Board board, int nextMove) {
        this.board = board;
        this.nextMove = nextMove;
    }

    @Override
    public void takeTurn(int move) {
        numberOfMovesMadeAtSpecificPosition++;
        positionOfMove = move;
        hasMadeMove = true;
        board.updateAt(move, X);
    }

    @Override
    public PlayerSymbol getWinningSymbol() {
        hasGotWinnersSymbol = true;
        return board.getWinningSymbol();
    }

    @Override
    public boolean hasWinner() {
        numberOfTimesBoardCheckedForWin++;
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
        boardCheckedForFreeSpaces = true;
        return board.hasFreeSpace();
    }

    @Override
    public int getCurrentPlayersNextMove() {
        numberOfTimesPlayerAskedForMove++;
        return nextMove;
    }

    @Override
    public boolean gameInProgress() {
        checkedGameIsInProgress = true;
        return board.hasFreeSpace() && !board.hasWinningCombination();
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }

    public boolean hasMadeMove() {
        return hasMadeMove;
    }

    public int getPositionOfMove() {
        return positionOfMove;
    }

    public boolean gameCheckedForWin() {
        return winnerChecked;
    }

    public boolean boardCheckedForFreeSpace() {
        return boardCheckedForFreeSpaces;
    }

    public boolean hasGotWinnersSymbol() {
        return hasGotWinnersSymbol;
    }

    public int numberOfMoves() {
        return numberOfMovesMadeAtSpecificPosition;
    }

    public int numberOfTimesPlayerAskedForMove() {
        return numberOfTimesPlayerAskedForMove;
    }

    public int numberOfTimesBoardCheckedForWin() {
        return numberOfTimesBoardCheckedForWin;
    }

    public int numberOfTimesBoardIsObtained() {
        return numberOfTimesBoardObtained;
    }

    public boolean gameInProgressCheck() {
        return checkedGameIsInProgress;
    }
}
