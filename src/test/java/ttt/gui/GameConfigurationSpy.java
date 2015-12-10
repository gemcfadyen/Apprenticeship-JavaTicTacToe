package ttt.gui;

import ttt.GameType;

import java.util.Arrays;
import java.util.List;

public class GameConfigurationSpy implements GameConfiguration {
    private boolean hasGotGameTypes = false;
    private boolean hasGotBoardDimensions = false;

    @Override
    public List<GameType> getGameTypes() {
        hasGotGameTypes = true;
        return Arrays.asList(GameType.HUMAN_VS_HUMAN);
    }

    @Override
    public String getDimension(GameType gameType) {
        hasGotBoardDimensions = true;
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

    public boolean hasObtainedBoardDimensions() {
        return hasGotBoardDimensions;
    }
}
