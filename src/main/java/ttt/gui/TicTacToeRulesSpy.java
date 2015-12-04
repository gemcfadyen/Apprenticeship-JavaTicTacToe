package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

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
    private boolean drawChecked = false;
    private boolean hasGotCurrentPlayer = false;

    public TicTacToeRulesSpy() {
    }

    public TicTacToeRulesSpy(Board board) {
        this.board = board;
    }

    @Override
    public void playMoveAt(String move) {
        positionOfMove = move;
        hasMadeMove = true;
    }

    @Override
    public PlayerSymbol getCurrentPlayer() {
        hasGotCurrentPlayer = true;
        return PlayerSymbol.X;
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
        hasInitialisedGame = true;
    }

    @Override
    public GameType getGameTypes() {
        hasGotGameTypes = true;
        return GameType.HUMAN_VS_HUMAN;
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

    public boolean hasGotCurrentPlayer() {
        return hasGotCurrentPlayer;
    }

    public boolean boardCheckedForFreeSpace() {
        return boardCheckedForFreeSpaces;
    }
}
