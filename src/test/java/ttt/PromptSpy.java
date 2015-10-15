package ttt;

public class PromptSpy implements Prompt {
    private Board lastBoardPrinted;
    private String lastMessagePrinted;

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
        lastMessagePrinted = "Congratulatory message printed";
    }

    @Override
    public void printDrawMessage() {
        lastMessagePrinted = "Draw message printed";
    }

    public String getLastMessagePrinted() {
        return lastMessagePrinted;
    }

    public String getLastBoardThatWasPrinted() {
        StringBuilder gridFormation = new StringBuilder();

        for (int i = 0; i < Board.NUMBER_OF_SLOTS; i++) {
            gridFormation.append(lastBoardPrinted.getSymbolAt(i));
        }
        return gridFormation.toString();
    }
}
