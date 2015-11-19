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
        Line[] columns = getColumns();

        copyRows(allLines, rows);
        copyColumns(allLines, rows.length, columns);
        copyDiagonals(allLines, rows.length + columns.length);

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

    private void copyDiagonals(Line[] allLines, int startingIndex) {
        allLines[startingIndex] = backslashDiagonal();
        allLines[startingIndex + 1] = forwardslashDiagonal();
    }

    private void copyColumns(Line[] allLines, int startingIndex, Line[] columns) {
        for (int i = 0; i < columns.length; i++) {
            allLines[i + startingIndex] = columns[i];
        }
    }

    private void copyRows(Line[] allLines, Line[] rows) {
        for (int i = 0; i < rows.length; i++) {
            allLines[i] = rows[i];
        }
    }
}
