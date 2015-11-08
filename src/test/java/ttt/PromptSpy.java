package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static ttt.PlayerSymbol.O;
import static ttt.PlayerSymbol.X;

public class PromptSpy implements Prompt {
    private final BufferedReader reader;
    private Board lastBoardPrinted;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;
    private int numberOfTimesClearIsCalled = 0;
    private int numberOfTimesXHasWon = 0;
    private int numberOfTimesOHasWon = 0;
    private int numberOfTimesPlayerIsReprompted = 0;

    public PromptSpy(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public int getNextMove(Board board) {
        try {
            return Integer.valueOf(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException("Error reading in PromptSpy");
        }
    }

    @Override
    public String getReplayOption() {
        try {
            numberOfTimesPlayerIsReprompted++;
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading in PromptSpy");
        }
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
}
