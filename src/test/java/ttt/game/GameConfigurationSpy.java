package ttt.game;

import java.util.Arrays;
import java.util.List;

import static ttt.game.GameType.HUMAN_VS_HUMAN;

public class GameConfigurationSpy implements GameConfiguration {
    private GameType gameType;
    private boolean hasGotGameTypes = false;

    public GameConfigurationSpy() {
        this.gameType = HUMAN_VS_HUMAN;
    }

    public GameConfigurationSpy(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public List<GameType> getGameTypes() {
        hasGotGameTypes = true;
        return Arrays.asList(gameType);
    }

    @Override
    public String getDimension(GameType gameType) {
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

}
