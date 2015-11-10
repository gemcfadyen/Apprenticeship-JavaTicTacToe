package ttt;

import static ttt.PlayerSymbol.VACANT;

public class Board {
    protected static final int BOARD_DIMENSION = 3;
    private static final int NUMBER_OF_SLOTS = BOARD_DIMENSION * BOARD_DIMENSION;
    private PlayerSymbol[] grid = new PlayerSymbol[BOARD_DIMENSION * BOARD_DIMENSION];

    public Board() {
        this(VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
    }

    public Board(PlayerSymbol... initialGridLayout) {
        this.grid = initialGridLayout;
    }

    public Line[] getRows() {
        LineGenerator lines = new LineGenerator(grid);
        return new Line[]{lines.topRow(), lines.middleRow(), lines.bottomRow()};
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
        for (int cellIndex = 0; cellIndex < NUMBER_OF_SLOTS; cellIndex++) {
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
        return index >= 0 && index < NUMBER_OF_SLOTS;
    }

    public boolean isVacantAt(int cellIndex) {
        return grid[cellIndex] == VACANT;
    }

    private boolean checkForWinIn(Line[] lines) {
        for (Line line : lines) {
           if(line.isWinning()) {
               return true;
           }
        }
        return false;
    }

    private PlayerSymbol getFirstSymbol(Line row) {
        return row.getSymbols()[0];
    }
}