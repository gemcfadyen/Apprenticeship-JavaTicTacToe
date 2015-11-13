package ttt;

public enum GameType {
    HUMAN_VS_HUMAN(1, "Human vs Human"),
    HUMAN_VS_UNBEATABLE(2, "Human vs Unbeatable"),
    UNBEATABLE_VS_HUMAN(3, "Unbeatable vs Human"),
    UNBEATABLE_VS_UNBEATABLE(4, "Unbeatable vs Unbeatable");


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
        for(GameType gameType : GameType.values()) {
            if(gameType.numericRepresentation() == numericRepresentation) {
                return gameType;
            }
        }
        return HUMAN_VS_HUMAN;
    }
}
