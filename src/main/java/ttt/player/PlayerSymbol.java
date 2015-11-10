package ttt.player;

public enum PlayerSymbol {
    X("X"), VACANT("-"), O("O");

    private String symbolForDisplay;

    PlayerSymbol(String symbol) {
        this.symbolForDisplay = symbol;
    }

    public String getSymbolForDisplay() {
        return symbolForDisplay;
    }
}
