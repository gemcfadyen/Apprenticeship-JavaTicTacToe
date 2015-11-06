package ttt;

public interface Prompt {
    int getNextMove(Board board);
    String readReplayOption();
    void askUserForTheirMove();
    void askUserToPlayAgain();
    void print(Board board);
    void printWinningMessageFor(PlayerSymbol symbol);
    void printDrawMessage();
    void clear();
}
