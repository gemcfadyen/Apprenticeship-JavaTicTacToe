package ttt.gui;

import ttt.GameType;

import java.util.Arrays;
import java.util.List;

import static ttt.GameType.HUMAN_VS_HUMAN;

public class GameConfigurationSpy implements GameConfiguration {
    private GameRules gameRules;
    private GameType gameType;
    private boolean hasGotGameTypes = false;
    private boolean hasGotBoardDimensions = false;
    private boolean hasInitialisedGame = false;

    public GameConfigurationSpy() {
        this.gameType = HUMAN_VS_HUMAN;
    }

    public GameConfigurationSpy(GameType gameType) {
        this.gameType = gameType;
    }

    public GameConfigurationSpy(GameType gameType, GameRules rulesToReturn) {
        this.gameType = gameType;
        this.gameRules = rulesToReturn;
    }

    @Override
    public List<GameType> getGameTypes() {
        hasGotGameTypes = true;
        return Arrays.asList(gameType);
    }

    @Override
    public String getDimension(GameType gameType) {
        hasGotBoardDimensions = true;
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    @Override
    public GameRules initialiseGame(GameType gameType, int dimension) {
        hasInitialisedGame = true;
        return gameRules;
    }

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

    public boolean hasObtainedBoardDimensions() {
        return hasGotBoardDimensions;
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }
}
