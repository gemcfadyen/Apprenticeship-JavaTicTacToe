package ttt;

public interface Prompt {
    String read();
    void askUserForTheirMove();
    void print(Board board);
    void printWinningMessageFor(PlayerSymbol symbol);
    void printDrawMessage();
    void clear();
}
