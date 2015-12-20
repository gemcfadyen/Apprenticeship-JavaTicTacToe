package ttt.gui;

import ttt.player.GuiHumanPlayer;
import ttt.player.PlayerSymbol;

public class GuiHumanPlayerSpy extends GuiHumanPlayer {
    private boolean preloadedWithMove = false;

    public GuiHumanPlayerSpy(PlayerSymbol symbol) {
        super(symbol);
    }

    public void setMove(int move) {
//        super.setMove(move);
        preloadedWithMove = true;
    }

    public boolean hasBeenPreloadedWithMove() {
        return preloadedWithMove;
    }
}
