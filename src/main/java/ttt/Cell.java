package ttt;

public class Cell {
    private final int offset;
    private PlayerSymbol symbol;

    public Cell(int offset, PlayerSymbol symbol) {
        this.offset = offset;
        this.symbol = symbol;
    }

    public int getOffset() {
        return offset;
    }

    public PlayerSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(PlayerSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }

        Cell other = (Cell)o;
        if(this.offset == other.getOffset()
                && this.symbol == other.getSymbol()) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 11;
        return prime * offset;
    }
}
