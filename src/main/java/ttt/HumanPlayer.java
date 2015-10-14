package ttt;

import static ttt.Board.NUMBER_OF_SLOTS;
import static ttt.PlayerSymbol.VACANT;

public class HumanPlayer {
    private Prompt prompt;
    private final PlayerSymbol symbol;

    public HumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        this.prompt = prompt;
        this.symbol = symbol;
    }

    public int chooseNextMoveFrom(Board board) {
        prompt.print(board);
        String usersInput = getNextMoveFromPrompt();

        while (!isNumber(usersInput) || outsideBoard(asInteger(usersInput)) || !hasFreeSpace(asInteger(usersInput), board)) {
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

    private boolean outsideBoard(int usersInput) {
        return !(usersInput >= 0 && usersInput < NUMBER_OF_SLOTS);
    }

    private boolean hasFreeSpace(int index, Board board) {
        return board.getSymbolAt(index) == VACANT;
    }

    private int asInteger(String usersInput) {
        return Integer.valueOf(usersInput);
    }

    private String getNextMoveFromPrompt() {
        prompt.askUserForTheirMove();
        return prompt.read();
    }
}
