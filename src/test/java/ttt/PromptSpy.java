package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class PromptSpy implements Prompt {
    private final BufferedReader reader;
    private Board lastBoardPrinted;
    private int numberOfTimesWinningMessageHasBeenPrinted = 0;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;
    private int numberOfTimesPlayerIsPrompted = 0;
    private int numberOfTimesBoardIsPrinted = 0;
    private int numberOfTimesClearIsCalled = 0;

    public PromptSpy() {
        this.reader = new BufferedReader(new StringReader(""));
    }

    public PromptSpy(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading in PromptSpy");
        }
    }

    @Override
    public void askUserForTheirMove() {
        numberOfTimesPlayerIsPrompted++;
    }

    @Override
    public void print(Board board) {
        numberOfTimesBoardIsPrinted++;
        this.lastBoardPrinted = board;
    }

    @Override
    public void printWinningMessage() {
        numberOfTimesWinningMessageHasBeenPrinted++;
    }

    @Override
    public void printDrawMessage() {
        numberOfTimesDrawMessageHasBeenPrinted++;
    }

    @Override
    public void clear() {
        numberOfTimesClearIsCalled++;
    }

    public int getNumberOfTimesWinningMessageHasBeenPrinted() {
        return numberOfTimesWinningMessageHasBeenPrinted;
    }

    public int getNumberOfTimesDrawMessageHasBeenPrinted() {
        return numberOfTimesDrawMessageHasBeenPrinted;
    }

    public int getNumberOfTimesPlayerIsPromptedForTheirMove() {
        return numberOfTimesPlayerIsPrompted;
    }

    public int getNumberOfTimesBoardIsPrinted() {
        return numberOfTimesBoardIsPrinted;
    }

    public int getNumberOfTimesClearIsCalled() {
        return numberOfTimesClearIsCalled;
    }

    public String getLastBoardThatWasPrinted() {
        StringBuilder gridFormation = new StringBuilder();
        Cell[][] rows = lastBoardPrinted.getRows();

        for (Cell[] cells : rows) {
            for (Cell cell : cells) {
                gridFormation.append(lastBoardPrinted.getSymbolAt(cell.getOffset()));
            }
        }

        return gridFormation.toString();
    }
}
