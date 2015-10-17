package ttt;

public class HumanPlayer extends Player {

    public HumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        prompt.print(board);
        String usersInput = getNextMoveFromPrompt();

        while (!validInput(board, usersInput)) {
            usersInput = getNextMoveFromPrompt();
        }

        return asInteger(usersInput);
    }

    private boolean validInput(Board board, String usersInput) {
        return isNumber(usersInput) && board.isValidPositionAt(asInteger(usersInput));
    }

    private boolean isNumber(String input) {
        try {
            asInteger(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private int asInteger(String usersInput) {
        return Integer.valueOf(usersInput);
    }

    private String getNextMoveFromPrompt() {
        prompt.askUserForTheirMove();
        return prompt.read();
    }
}
