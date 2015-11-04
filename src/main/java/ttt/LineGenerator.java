package ttt;

public class LineGenerator {
    private final Cell[] grid;

    public LineGenerator(Cell[] grid) {
        this.grid = grid;
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

    public Cell[] leftColumn() {
        return new Cell[]{grid[0], grid[3], grid[6]};
    }

    public Cell[] middleColumn() {
        return new Cell[]{grid[1], grid[4], grid[7]};
    }

    public Cell[] rightColumn() {
        return new Cell[]{grid[2], grid[5], grid[8]};
    }

    public Cell[] forwardslashDiagonal() {
        return new Cell[]{grid[2], grid[4], grid[6]};
    }

    public Cell[] backslashDiagonal() {
        return new Cell[]{grid[0], grid[4], grid[8]};
    }
}
