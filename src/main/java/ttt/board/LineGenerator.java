package ttt.board;

import ttt.player.PlayerSymbol;

import java.util.Arrays;

import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class LineGenerator {
    private final PlayerSymbol[] grid;

    public LineGenerator(PlayerSymbol... grid) {
        this.grid = grid;
    }

    public Line[] linesForAllDirections() {
        return new Line[] {topRow(), middleRow(), bottomRow(),
                leftColumn(), middleColumn(), rightColumn(),
                backslashDiagonal(), forwardslashDiagonal()};
    }

    public Line topRow() {
        return new Line(grid[0], grid[1], grid[2]);
    }

    public Line middleRow() {
        return new Line(grid[3], grid[4], grid[5]);
    }

    public Line bottomRow() {
        return new Line(grid[6], grid[7], grid[8]);
    }

    private Line leftColumn() {
        return new Line(grid[0], grid[3], grid[6]);
    }

    private Line middleColumn() {
        return new Line(grid[1], grid[4], grid[7]);
    }

    private Line rightColumn() {
        return new Line(grid[2], grid[5], grid[8]);
    }

    private Line forwardslashDiagonal() {
        return new Line(grid[2], grid[4], grid[6]);
    }

    private Line backslashDiagonal() {
        return new Line(grid[0], grid[4], grid[8]);
    }
}
