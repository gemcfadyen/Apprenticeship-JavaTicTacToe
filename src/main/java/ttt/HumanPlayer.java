package ttt;

public class HumanPlayer extends Player {

    public HumanPlayer(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        String usersInput = getPlayersNextMove(board);
        while (!validInput(board, usersInput)) {
            prompt.clear();
            usersInput = getPlayersNextMove(board);
        }

        prompt.clear();
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

    private String getPlayersNextMove(Board board) {
        prompt.print(board);
        prompt.askUserForTheirMove();
        return prompt.read();
    }
}
