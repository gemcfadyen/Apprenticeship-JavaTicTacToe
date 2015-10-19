package ttt;

import com.sun.jdi.connect.Connector;

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

        for (int i = 0; i < BOARD_DIMENSION * BOARD_DIMENSION; i++) {
            gridFormation.append(lastBoardPrinted.getSymbolAt(i));
        }
        return gridFormation.toString();
    }
}
