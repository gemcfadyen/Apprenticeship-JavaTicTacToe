package ttt;

import java.io.IOException;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayer {
    private Prompt prompt;
    private final String symbol;

    public HumanPlayer(Prompt prompt, String symbol) {
        this.prompt = prompt;
        this.symbol = symbol;
    }

    public int chooseNextMoveFrom(Board board) throws IOException {
        prompt.print(board);
        String usersInput = getNextMoveFromPrompt();

        while (!isNumber(usersInput)
                || outsideBoard(asInteger(usersInput))
                || !hasFreeSpace(asInteger(usersInput), board)) {
            usersInput = getNextMoveFromPrompt();
        }

        return asInteger(usersInput);
    }

    private boolean outsideBoard(Integer usersInput) {
        if (usersInput >= 0 && usersInput < 9) {
            return false;
        }
        return true;
    }

    private Integer asInteger(String usersInput) {
        return Integer.valueOf(usersInput);
    }

    private boolean hasFreeSpace(Integer index, Board board) {
        if (board.getSymbolAt(index) == "-") {
            return true;
        }
        return false;
    }

    private String getNextMoveFromPrompt() throws IOException {
        prompt.askUserForTheirMove();
        return prompt.read();
    }

    private boolean isNumber(String input) {
        try {
            asInteger(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String getSymbols() {
        return symbol;
    }
}
