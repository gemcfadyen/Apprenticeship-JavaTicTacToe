package ttt;

import java.io.IOException;

import static ttt.Board.NUMBER_OF_SLOTS;
import static ttt.PlayerSymbol.VACANT;

/**
 * Created by Georgina on 12/10/15.
 */
public class HumanPlayer {
    private Prompt prompt;
    private final PlayerSymbol symbol;

    public HumanPlayer(Prompt prompt, PlayerSymbol symbol) {
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

    public PlayerSymbol getSymbols() {
        return symbol;
    }

    private boolean isNumber(String input) {
        try {
            asInteger(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean outsideBoard(Integer usersInput) {
        if (usersInput >= 0 && usersInput < NUMBER_OF_SLOTS) {
            return false;
        }
        return true;
    }

    private boolean hasFreeSpace(Integer index, Board board) {
        if (board.getSymbolAt(index) == VACANT) {
            return true;
        }
        return false;
    }

    private Integer asInteger(String usersInput) {
        return Integer.valueOf(usersInput);
    }

    private String getNextMoveFromPrompt() throws IOException {
        prompt.askUserForTheirMove();
        return prompt.read();
    }
}
