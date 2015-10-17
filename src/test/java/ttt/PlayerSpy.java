package ttt;

public class PlayerSpy extends Player {
    private int numberOfTurnsTaken;

    public PlayerSpy(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
        this.numberOfTurnsTaken = 0;
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        numberOfTurnsTaken++;
        return Integer.valueOf(prompt.read());
    }

    public int numberOfTurnsTaken() {
        return numberOfTurnsTaken;
    }
}
