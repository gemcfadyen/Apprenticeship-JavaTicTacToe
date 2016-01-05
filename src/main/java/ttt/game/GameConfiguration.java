package ttt.game;

import java.util.List;

public interface GameConfiguration {
    List<GameType> getGameTypes();
    String getDimension(GameType gameType);
}
