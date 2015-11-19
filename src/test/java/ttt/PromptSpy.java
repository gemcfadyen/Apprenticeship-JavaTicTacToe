package ttt;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class PromptSpy implements Prompt {
    private final BufferedReader reader;
    private Board lastBoardPrinted;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;
    private int numberOfTimesXHasWon = 0;
    private int numberOfTimesOHasWon = 0;
    private int numberOfTimesPlayerIsReprompted = 0;
    private int numberOfTimesPlayerOptionsHaveBeenPrinted;
    private int numberOfTimesBoardDimensionPrompted = 0;

    @Override
    public int getBoardDimension(GameType gameType) {
        numberOfTimesBoardDimensionPrompted++;
        return Integer.valueOf(readInput());
    }

    public PromptSpy(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public GameType getGameType() {
        numberOfTimesPlayerOptionsHaveBeenPrinted++;
        return GameType.of(Integer.valueOf(readInput()));
    }

    @Override
    public ReplayOption getReplayOption() {
        numberOfTimesPlayerIsReprompted++;
        return ReplayOption.of(readInput());
    }

    @Override
    public int getNextMove(Board board) {
        return Integer.valueOf(readInput());
    }

    @Override
    public void print(Board board) {
        this.lastBoardPrinted = board;
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        if (symbol == X) {
            numberOfTimesXHasWon++;
        }

        if (symbol == O) {
            numberOfTimesOHasWon++;
        }
    }

    @Override
    public void printDrawMessage() {
        numberOfTimesDrawMessageHasBeenPrinted++;
    }

    private String readInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading in PromptSpy");
        }
    }

    public int getNumberOfTimesXHasWon() {
        return numberOfTimesXHasWon;
    }

    public int getNumberOfTimesOHasWon() {
        return numberOfTimesOHasWon;
    }

    public int getNumberOfTimesDrawMessageHasBeenPrinted() {
        return numberOfTimesDrawMessageHasBeenPrinted;
    }

    public String getLastBoardThatWasPrinted() {
        StringBuilder gridFormation = new StringBuilder();
        Line[] rows = lastBoardPrinted.getRows();

        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
                gridFormation.append(symbol);
            }
        }

        return gridFormation.toString();
    }

    public int getNumberOfTimesPlayerIsPromptedToPlayAgain() {
        return numberOfTimesPlayerIsReprompted;
    }

    public int getNumberOfTimesPromptedForPlayerOption() {
        return numberOfTimesPlayerOptionsHaveBeenPrinted;
    }

    public int getNumberOfTimesBoardDimensionPrompted() {
        return numberOfTimesBoardDimensionPrompted;
    }
}
