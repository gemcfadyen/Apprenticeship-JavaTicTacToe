package ttt;

import ttt.board.Board;
import ttt.board.BoardFactory;

public class BoardFactoryStub extends BoardFactory {
    private Board[] boards;
    private int boardIndex = 0;
    private int dimension;

    public BoardFactoryStub(Board... boardsToReturn) {
        this.boards = boardsToReturn;
    }

    public Board createBoardWithSize(int dimension) {
        this.dimension = dimension;
        optionallyCreateDefaultBoard(dimension);
        return boards[boardIndex++];
    }

    private void optionallyCreateDefaultBoard(int dimension) {
        if (boards.length == 0) {
            boards = new Board[]{new Board(dimension)};
        }
    }

    public int getDimension() {
        return dimension;
    }

    public Board getLatestBoard() {
        return boards[boardIndex - 1];
    }
}
