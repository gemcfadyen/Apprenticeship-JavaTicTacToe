package ttt;

public interface Player {
    int chooseNextMoveFrom(Board board);

    PlayerSymbol getSymbol();
}
