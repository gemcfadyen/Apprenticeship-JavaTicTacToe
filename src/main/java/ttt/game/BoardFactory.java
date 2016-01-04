package ttt.game;

public class BoardFactory {
    public Board createBoardWithSize(int dimension) {
        return new Board(dimension);
    }
}
