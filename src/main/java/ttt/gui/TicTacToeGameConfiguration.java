package ttt.gui;

import ttt.GameType;

import java.util.Arrays;
import java.util.List;

public class TicTacToeGameConfiguration implements GameConfiguration {

    @Override
    public List<GameType> getGameTypes() {
        return Arrays.asList(GameType.values());
    }

    @Override
    public String getDimension(GameType gameType) {
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

}
