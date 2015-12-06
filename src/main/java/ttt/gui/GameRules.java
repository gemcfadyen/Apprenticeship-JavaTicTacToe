package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

import java.util.List;

public interface GameRules {

    void playMoveAt(String move);

    PlayerSymbol getCurrentPlayer();

    boolean hasWinner();

    void togglePlayer();

    void initialiseGame(String dimension);

    List<GameType> getGameTypes();

    String getDimension(GameType gameType);

    Board getBoard();

    void storeGameType(GameType gameType);

    boolean boardHasFreeSpace();
}
