package ttt.game;

public interface GameRules {
    void initialiseGame(GameType gameType, int dimension);
    void playGame();
    boolean hasWinner();
    boolean noWinnerYet();
    PlayerSymbol getWinningSymbol();
    Board getBoard();
    boolean hasAvailableMoves();
    Player getCurrentPlayer();
}
