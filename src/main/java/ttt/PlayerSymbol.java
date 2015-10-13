package ttt;

public enum PlayerSymbol {
    X("X"), VACANT("-"), O("O");

    private String symbolForDisplay;

    PlayerSymbol(String symbol) {
        this.symbolForDisplay = symbol;
    }

    String getSymbolForDisplay() {
        return symbolForDisplay;
    }
}
