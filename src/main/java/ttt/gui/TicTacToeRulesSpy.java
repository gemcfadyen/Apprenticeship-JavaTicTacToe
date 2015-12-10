package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import static ttt.player.PlayerSymbol.X;

public class TicTacToeRulesSpy implements GameRules {
    private Board board;
    private boolean hasInitialisedGame = false;
    private boolean hasMadeMove = false;
    private String positionOfMove;
    private boolean hasToggledPlayer = false;
    private boolean winnerChecked = false;
    private boolean boardCheckedForFreeSpaces = false;
    private String nextMove;
    private boolean hasGotWinnersSymbol = false;

    public TicTacToeRulesSpy() {
    }

    public TicTacToeRulesSpy(Board board, String nextMove) {
        this.board = board;
        this.nextMove = nextMove;
    }

    @Override
    public void playMoveAt(String move) {
        positionOfMove = move;
        hasMadeMove = true;
        board.updateAt(Integer.valueOf(nextMove), X);
    }

    @Override
    public PlayerSymbol getCurrentPlayerSymbol() {
        return X;
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
    public void togglePlayer() {
        hasToggledPlayer = true;
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
        return board;
    }

    @Override
    public boolean boardHasFreeSpace() {
        boardCheckedForFreeSpaces = true;
        return board.hasFreeSpace();
    }

    @Override
    public String getCurrentPlayersNextMove() {
        return nextMove;
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }

    public boolean hasMadeMove() {
        return hasMadeMove;
    }

    public String getPositionOfMove() {
        return positionOfMove;
    }

    public boolean hasToggledPlayers() {
        return hasToggledPlayer;
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
}
