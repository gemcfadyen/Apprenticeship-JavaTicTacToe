package ttt.game;

public enum ReplayOption {
    N, Y;

    public static ReplayOption of(String input) {
        if (Y.name().equals(input)) {
            return Y;
        }
        return N;
    }
}
