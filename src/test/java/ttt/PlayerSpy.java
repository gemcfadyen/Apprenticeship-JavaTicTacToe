package ttt;

public class PlayerSpy implements Player {
    private int numberOfTurnsTaken;
    private Prompt prompt;
    private PlayerSymbol symbol;

    public PlayerSpy(Prompt prompt, PlayerSymbol symbol) {
        this.prompt = prompt;
        this.symbol = symbol;
        this.numberOfTurnsTaken = 0;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        numberOfTurnsTaken++;
        return Integer.valueOf(prompt.read());
    }

    @Override
    public PlayerSymbol getSymbol() {
        return symbol;
    }

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
