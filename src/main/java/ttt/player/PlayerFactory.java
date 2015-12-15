package ttt.player;

import ttt.GameType;

public interface PlayerFactory {
    Player[] createPlayers(GameType playerOption, int dimension);
}
