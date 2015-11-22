package ttt;

import ttt.board.Board;
import ttt.board.BoardFactory;

public class BoardFactoryStub extends BoardFactory {
    private final Board[] boards;
    private int boardIndex = 0;
    private int dimension;

    public BoardFactoryStub(Board... boardsToReturn) {
        this.boards = boardsToReturn;
    }

    public Board createBoardWithSize(int dimension) {
        this.dimension = dimension;
        return boards[boardIndex++];
    }

    public int getDimension() {
        return dimension;
    }
}
