package ttt;

public class PromptSpy implements Prompt {
    private Board lastBoardPrinted;
    private String lastMessagePrinted;
    private int numberOfTimesWinningMessageHasBeenPrinted = 0;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;

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

        for (int i = 0; i < Board.NUMBER_OF_SLOTS; i++) {
            gridFormation.append(lastBoardPrinted.getSymbolAt(i));
        }
        return gridFormation.toString();
    }
}
