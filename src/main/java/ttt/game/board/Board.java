package ttt.game.board;

import ttt.game.PlayerSymbol;

import java.util.ArrayList;
import java.util.List;

import static ttt.game.PlayerSymbol.VACANT;

public class Board {
    private int dimension;
    private int numberOfSlots;
    private PlayerSymbol[] grid;

    public Board(PlayerSymbol... initialGridLayout) {
        this.dimension = (int) Math.sqrt(initialGridLayout.length);
        this.numberOfSlots = dimension * dimension;
        this.grid = initialGridLayout;
    }

    public Board(int dimension) {
        this.dimension = dimension;
        this.numberOfSlots = dimension * dimension;

        PlayerSymbol[] initialisation = new PlayerSymbol[numberOfSlots];
        for (int i = 0; i < numberOfSlots; i++) {
            initialisation[i] = VACANT;
        }

        this.grid = initialisation;
    }

    public List<Line> getRows() {
        LineGenerator lines = new LineGenerator(grid);
        return lines.getRows();
    }

    public boolean hasWinningCombination() {
        LineGenerator lineGenerator = new LineGenerator(grid);
        return checkForWinIn(lineGenerator.linesForAllDirections());
    }

    public PlayerSymbol getWinningSymbol() {
        if (hasWinningCombination()) {

            for (Line line : new LineGenerator(grid).linesForAllDirections()) {
                if (line.isWinning()) {
                    return getFirstSymbol(line);
                }
            }
        }
        return VACANT;
    }

    public PlayerSymbol getSymbolAt(int index) {
        return grid[index];
    }

    public boolean hasFreeSpace() {
        for (int cellIndex = 0; cellIndex < numberOfSlots; cellIndex++) {
            if (isVacantAt(cellIndex)) {
                return true;
            }
        }
        return false;
    }

    public void updateAt(int index, PlayerSymbol symbol) {
        grid[index] = symbol;
    }

    public boolean isWithinGridBoundary(int index) {
        return index >= 0 && index < numberOfSlots;
    }

    public boolean isVacantAt(int cellIndex) {
        return grid[cellIndex] == VACANT;
    }

    private boolean checkForWinIn(List<Line> lines) {
        for (Line line : lines) {
            if (line.isWinning()) {
                return true;
            }
        }
        return false;
    }

    private PlayerSymbol getFirstSymbol(Line row) {
        return row.getSymbols()[0];
    }

    public List<Integer> getVacantPositions() {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 0; i < numberOfSlots; i++) {
            if (isVacantAt(i)) {
                freePositions.add(i);
            }
        }

        return freePositions;
    }
}