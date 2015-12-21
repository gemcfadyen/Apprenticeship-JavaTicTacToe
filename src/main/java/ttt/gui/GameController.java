package ttt.gui;

import ttt.GameType;
import ttt.player.Player;

public interface GameController {
    void presentGameTypes();
    void presentBoardDimensionsFor(GameType gameType);
    void presentBoard(int dimension);
    void playMove(int position);
    Player getCurrentPlayer();
}
