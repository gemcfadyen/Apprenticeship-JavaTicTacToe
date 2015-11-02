package ttt;

import com.sun.jdi.connect.Connector;

import java.util.List;

import static ttt.Board.*;

public class PromptSpy implements Prompt {
    private Board lastBoardPrinted;
    private int numberOfTimesWinningMessageHasBeenPrinted = 0;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;

    public PromptSpy() {
    }

    @Override
    public String read() {
        return null;
    }

    @Override
    public void askUserForTheirMove() {

    }

    @Override
    public void print(Board board) {
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

    public int getNumberOfTimesWinningMessageHasBeenPrinted() {
        return numberOfTimesWinningMessageHasBeenPrinted;
    }

    public int getNumberOfTimesDrawMessageHasBeenPrinted() {
        return numberOfTimesDrawMessageHasBeenPrinted;
    }

    public String getLastBoardThatWasPrinted() {
        StringBuilder gridFormation = new StringBuilder();

        Cell[][] rows = lastBoardPrinted.getRows();

        for(Cell[] cells: rows) {
            for(int i = 0; i < cells.length; i++) {
                gridFormation.append(lastBoardPrinted.getSymbolAt(cells[i].getOffset()));
            }
        }

        return gridFormation.toString();
    }
}
