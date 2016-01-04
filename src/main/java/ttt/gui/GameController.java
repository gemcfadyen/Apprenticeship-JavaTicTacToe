package ttt.gui;

import ttt.GameType;
import ttt.player.Player;

public interface GameController {
    void presentGameTypes();
    void presentBoardDimensionsFor(GameType gameType);
    void startGame(int dimension);
    void takeMove(int position);
    Player getCurrentPlayer();
}
