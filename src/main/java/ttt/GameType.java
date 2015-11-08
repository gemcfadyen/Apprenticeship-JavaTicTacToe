package ttt;

public enum GameType {
    HUMAN_VS_HUMAN(1);


    private final int playerSetup;

    GameType(int playerSetup) {
        this.playerSetup = playerSetup;
    }

    public int numericRepresentation() {
        return playerSetup;
    }
}
