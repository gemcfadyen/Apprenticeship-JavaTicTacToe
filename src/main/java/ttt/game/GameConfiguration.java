package ttt.game;

import ttt.game.GameType;

import java.util.List;

public interface GameConfiguration {
    List<GameType> getGameTypes();
    String getDimension(GameType gameType);
}
