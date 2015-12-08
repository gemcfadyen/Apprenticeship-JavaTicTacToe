package ttt.ui;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.List;

import static ttt.player.PlayerSymbol.VACANT;
import static ttt.player.PlayerSymbol.X;

public class PrettyFormatter implements DisplayFormatter {
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String BOARD_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String X_COLOUR_ANSII_CHARACTERS = "\033[1;33m";
    private static final String O_COLOUR_ANSII_CHARACTERS = "\033[1;31m";

    @Override
    public String formatForDisplay(Board board) {
        String boardForDisplay = BOARD_COLOUR_ANSII_CHARACTERS + newLine();

        List<Line> rows = board.getRows();
        int dimension = rows.size();
        int offset = 0;
        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
                boardForDisplay +=
                        space()
                                + displayCell(symbol, offset)
                                + getBorderFor(offset, dimension);
                offset++;
            }
        }
        return boardForDisplay;
    }

    @Override
    public String formatWinningMessage(PlayerSymbol symbol) {
        return FONT_COLOUR_ANSII_CHARACTERS
                + "Congratulations - "
                + colour(symbol)
                + FONT_COLOUR_ANSII_CHARACTERS
                + " has won";
    }

    @Override
    public String formatBoardDimensionMessage(int largestDimension) {
       return FONT_COLOUR_ANSII_CHARACTERS
              + "Please enter the dimension of the board you would like to use [" + 1 + " to " + largestDimension + "]";
    }

    @Override
    public String applyInvalidColour(String message) {
        return BOARD_COLOUR_ANSII_CHARACTERS + message;
    }

    @Override
    public String applyFontColour(String message) {
        return FONT_COLOUR_ANSII_CHARACTERS + message;

    }

    private String displayCell(PlayerSymbol symbol, int cellOffset) {
        if (symbol == VACANT) {
            return optionallyPad(cellOffset) + colour(cellOffset);
        } else {
            return space() + colour(symbol);
        }
    }

    private String colour(PlayerSymbol symbol) {
        if (symbol.equals(X)) {
            return X_COLOUR_ANSII_CHARACTERS + symbol.getSymbolForDisplay();
        }
        return O_COLOUR_ANSII_CHARACTERS + symbol.getSymbolForDisplay();
    }

    private String colour(int cellOffset) {
        return NUMBER_COLOUR_ANSII_CHARACTERS + String.valueOf(cellOffset + 1);
    }

    private String getBorderFor(int position, int dimension) {
        String border;
        if (lastRow(position, dimension)) {
            border = space();
        } else if (endOfRow(position, dimension)) {
            border = dividingHorizontalLine(dimension);
        } else {
            border = space() + dividingVerticalLine();
        }
        return BOARD_COLOUR_ANSII_CHARACTERS + border;
    }

    private String optionallyPad(int position) {
        if (singleDigit(position)) {
            return space();
        }
        return "";
    }

    private String dividingVerticalLine() {
        return "|";
    }

    private String dividingHorizontalLine(int dimension) {
        String dividingLine = space() + newLine();
        for (int i = 0; i < dimension; i++) {
            dividingLine += "-----";
        }
        dividingLine += newLine();
        return dividingLine;
    }

    private String newLine() {
        return "\n";
    }

    private boolean singleDigit(int position) {
        return position < 9;
    }

    private String space() {
        return " ";
    }

    private boolean lastRow(int index, int dimension) {
        return index == (dimension * dimension - 1);
    }

    private boolean endOfRow(int index, int dimension) {
        return (index + 1) % dimension == 0;
    }

}