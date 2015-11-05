package ttt;

public class LineGenerator {
    private final Cell[] grid;

    public LineGenerator(Cell[] grid) {
        this.grid = grid;
    }

    public Cell[][] linesForAllDirections() {
        return new Cell[][] {topRow(), middleRow(), bottomRow(),
                leftColumn(), middleColumn(), rightColumn(),
                backslashDiagonal(), forwardslashDiagonal()};
    }

    public Cell[] topRow() {
        return new Cell[]{grid[0], grid[1], grid[2]};
    }

    public Cell[] middleRow() {
        return new Cell[]{grid[3], grid[4], grid[5]};
    }

    public Cell[] bottomRow() {
        return new Cell[]{grid[6], grid[7], grid[8]};
    }

    private Cell[] leftColumn() {
        return new Cell[]{grid[0], grid[3], grid[6]};
    }

    private Cell[] middleColumn() {
        return new Cell[]{grid[1], grid[4], grid[7]};
    }

    private Cell[] rightColumn() {
        return new Cell[]{grid[2], grid[5], grid[8]};
    }

    private Cell[] forwardslashDiagonal() {
        return new Cell[]{grid[2], grid[4], grid[6]};
    }

    private Cell[] backslashDiagonal() {
        return new Cell[]{grid[0], grid[4], grid[8]};
    }
}
