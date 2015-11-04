package ttt;

public class LineGenerator {
    private final Cell[] grid;

    public LineGenerator(Cell[] grid) {
        this.grid = grid;
    }

    public Cell[][] linesForAllDirections() {
        Cell[][] allLines = new Cell[8][];
        allLines[0] = topRow();
        allLines[1] = middleRow();
        allLines[2] = bottomRow();
        allLines[3] = leftColumn();
        allLines[4] = middleColumn();
        allLines[5] = rightColumn();
        allLines[6] = backslashDiagonal();
        allLines[7] = forwardslashDiagonal();

        return allLines;
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
