package ttt.gui;

import ttt.GameType;

import java.util.List;

public interface GameConfiguration {
    List<GameType> getGameTypes();
    String getDimension(GameType gameType);
    GameRules initialiseGame(GameType gameType, int dimension);
}
