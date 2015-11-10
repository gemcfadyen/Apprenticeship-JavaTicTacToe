package ttt;

public enum GameType {
    HUMAN_VS_HUMAN(1, "Human vs Human");


    private final int gameType;
    private final String gameName;

    GameType(int gameType, String gameName) {
        this.gameType = gameType;
        this.gameName = gameName;
    }

    public int numericRepresentation() {
        return gameType;
    }

    public String gameNameForDisplay() {
        return gameName;
    }


    public static GameType of(int numericRepresentation) {
        return HUMAN_VS_HUMAN;
    }
}
