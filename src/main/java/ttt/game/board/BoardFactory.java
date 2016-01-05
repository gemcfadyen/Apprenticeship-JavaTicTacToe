package ttt.game.board;

public class BoardFactory {
    public Board createBoardWithSize(int dimension) {
        return new Board(dimension);
    }
}
