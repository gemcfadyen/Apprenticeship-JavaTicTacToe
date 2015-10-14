package ttt;

public interface Prompt {
    String read();
    void askUserForTheirMove();
    void print(Board board);
    void printWinningMessage();
    void printDrawMessage();
}
