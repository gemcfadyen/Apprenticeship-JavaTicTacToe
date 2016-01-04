package ttt.game;

import ttt.game.Board;
import ttt.game.BoardFactory;

public class BoardFactoryStub extends BoardFactory {
    private Board[] boards;
    private int boardIndex = 0;

    public BoardFactoryStub(Board... boardsToReturn) {
        this.boards = boardsToReturn;
    }

    public Board createBoardWithSize(int dimension) {
        optionallyCreateDefaultBoard(dimension);
        return boards[boardIndex++];
    }

    private void optionallyCreateDefaultBoard(int dimension) {
        if (boards.length == 0) {
            boards = new Board[]{new Board(dimension)};
        }
    }

}
