package ttt.game;

public enum PlayerSymbol {
    X("X"), VACANT("-"), O("O");

    private String symbolForDisplay;

    PlayerSymbol(String symbol) {
        this.symbolForDisplay = symbol;
    }

    public String getSymbolForDisplay() {
        return symbolForDisplay;
    }

    public static PlayerSymbol opponent(PlayerSymbol playerSymbol) {
        if(X.equals(playerSymbol)) {
            return O;
        }
        return X;
    }
}
