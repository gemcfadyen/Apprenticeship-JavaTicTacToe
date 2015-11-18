package ttt.board;

import ttt.player.PlayerSymbol;

public class LineGenerator {
    private final PlayerSymbol[] grid;
    private int dimension;

    public LineGenerator(PlayerSymbol... grid) {
        this.grid = grid;
        this.dimension = (int) Math.sqrt(grid.length);
    }

    public Line[] linesForAllDirections() {
        Line[] allLines = new Line[(dimension * 2) + 2];

        Line[] rows = getRows();
        for (int i = 0; i < rows.length; i++) {
            allLines[i] = rows[i];
        }

        Line[] columns = getColumns();

        for (int i = 0; i < columns.length; i++) {
            allLines[i + rows.length] = columns[i];
        }

        int index = rows.length + columns.length;

        allLines[index] = backslashDiagonal();
        allLines[index + 1] = forwardslashDiagonal();
        return allLines;
    }

    public Line[] getRows() {
        Line[] horizontals = new Line[dimension];

        int startingIndex = 0;
        for (int rowNumber = 0; rowNumber < dimension; rowNumber++) {
            PlayerSymbol[] symbols = new PlayerSymbol[dimension];
            for (int i = 0; i < dimension; i++) {
                symbols[i] = grid[i + startingIndex];
            }
            startingIndex += dimension;

            horizontals[rowNumber] = new Line(symbols);
        }
        return horizontals;
    }

    private Line[] getColumns() {
        Line[] columns = new Line[dimension];

        for (int rowNumber = 0; rowNumber < dimension; rowNumber++) {
            PlayerSymbol[] symbols = new PlayerSymbol[dimension];
            int offset = rowNumber;
            for (int i = 0; i < dimension; i++) {
                symbols[i] = grid[offset];
                offset += dimension;
            }

            columns[rowNumber] = new Line(symbols);
        }

        return columns;
    }

    private Line forwardslashDiagonal() {
        int offset = dimension - 1;
        PlayerSymbol[] symbols = new PlayerSymbol[dimension];
        for (int i = 0; i < dimension; i++) {
            symbols[i] = grid[offset];
            offset += dimension - 1;
        }
        return new Line(symbols);
    }

    private Line backslashDiagonal() {
        PlayerSymbol[] symbols = new PlayerSymbol[dimension];
        int offset = 0;
        for (int i = 0; i < dimension; i++) {
            symbols[i] = grid[offset];
            offset += dimension + 1;
        }
        return new Line(symbols);
    }
}
