package ttt;

public interface Prompt {
    int getNextMove(Board board);
    String getReplayOption();
    void print(Board board);
    void printWinningMessageFor(PlayerSymbol symbol);
    void printDrawMessage();
    void clear();
}
