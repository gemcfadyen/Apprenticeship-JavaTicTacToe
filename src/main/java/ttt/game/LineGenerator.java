package ttt.game;

import java.util.ArrayList;
import java.util.List;

public class LineGenerator {
    private final PlayerSymbol[] grid;
    private int dimension;

    public LineGenerator(PlayerSymbol... grid) {
        this.grid = grid;
        this.dimension = (int) Math.sqrt(grid.length);
    }

    public List<Line> linesForAllDirections() {
        List<Line> allLines = new ArrayList<>();
        allLines.addAll(getRows());
        allLines.addAll(getColumns());
        allLines.addAll(getDiagonals());

        return allLines;
    }

    public List<Line> getRows() {
        List<Line> horizontals = new ArrayList<>();

        int startingIndex = 0;
        for (int rowNumber = 0; rowNumber < dimension; rowNumber++) {
            PlayerSymbol[] symbols = new PlayerSymbol[dimension];
            for (int i = 0; i < dimension; i++) {
                symbols[i] = grid[i + startingIndex];
            }
            startingIndex += dimension;

            horizontals.add(new Line(symbols));
        }
        return horizontals;
    }

    private List<Line> getColumns() {
        List<Line> columns = new ArrayList<>();

        for (int rowNumber = 0; rowNumber < dimension; rowNumber++) {
            PlayerSymbol[] symbols = new PlayerSymbol[dimension];
            int offset = rowNumber;
            for (int i = 0; i < dimension; i++) {
                symbols[i] = grid[offset];
                offset += dimension;
            }

            columns.add(new Line(symbols));
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

    private List<Line> getDiagonals() {
        List<Line> diagonals = new ArrayList<>();
        diagonals.add(backslashDiagonal());
        diagonals.add(forwardslashDiagonal());

        return diagonals;
    }
}
