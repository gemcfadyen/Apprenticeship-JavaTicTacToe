package ttt;

public enum GameType {
    HUMAN_VS_HUMAN("Human vs Human", 1, 5),
    HUMAN_VS_UNBEATABLE("Human vs Unbeatable", 2, 4),
    UNBEATABLE_VS_HUMAN("Unbeatable vs Human", 3, 4);

    private final int promptNumber;
    private final int maximumDimension;
    private final String gameName;

    GameType(String gameName, int promptNumber, int maximumDimension) {
        this.gameName = gameName;
        this.promptNumber = promptNumber;
        this.maximumDimension = maximumDimension;
    }

    public int numericRepresentation() {
        return promptNumber;
    }

    public String gameNameForDisplay() {
        return gameName;
    }

    public int dimensionLowerBoundary() {
        return 2;
    }

    public int dimensionUpperBoundary() {
        return maximumDimension;
    }

    public static GameType of(int numericRepresentation) {
        for (GameType gameType : values()) {
            if (gameType.numericRepresentation() == numericRepresentation) {
                return gameType;
            }
        }
        return HUMAN_VS_HUMAN;
    }

    public static GameType of(String displayName) {
        for (GameType gameType : values()) {
            if (gameType.gameNameForDisplay().equals(displayName)) {
                return gameType;
            }
        }
        return HUMAN_VS_HUMAN;
    }
}

