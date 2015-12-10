package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import java.util.Arrays;
import java.util.List;

import static ttt.player.PlayerSymbol.*;

public class TicTacToeRulesSpy implements GameRules {
    private Board board;
    private boolean hasGotGameTypes = false;
    private boolean hasGotBoardDimensions = false;
    private boolean hasInitialisedGame = false;
    private boolean hasStoredGameType = false;
    private boolean hasMadeMove = false;
    private String positionOfMove;
    private boolean hasToggledPlayer = false;
    private boolean winnerChecked = false;
    private boolean boardCheckedForFreeSpaces = false;
    private boolean hasGotCurrentPlayer = false;
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
        hasGotCurrentPlayer = true;
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
    public void initialiseGame(String dimension) {
        if (board == null) {
            board = new Board(Integer.valueOf(dimension));
        }
        hasInitialisedGame = true;
    }

    @Override
    public List<GameType> getGameTypes() {
        hasGotGameTypes = true;
        return Arrays.asList(GameType.HUMAN_VS_HUMAN);
    }

    @Override
    public String getDimension(GameType gameType) {
        hasGotBoardDimensions = true;
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void storeGameType(GameType gameType) {
        hasStoredGameType = true;
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

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

    public boolean hasObtainedBoardDimensions() {
        return hasGotBoardDimensions;
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }

    public boolean hasStoredGameType() {
        return hasStoredGameType;
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
