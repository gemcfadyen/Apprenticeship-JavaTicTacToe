package ttt.guiapp;

import ttt.game.PlayerSymbol;

public class GuiHumanPlayerSpy extends GuiHumanPlayer {
    private boolean preloadedWithMove = false;

    public GuiHumanPlayerSpy(PlayerSymbol symbol) {
        super(symbol);
    }

    public void update(int move) {
        preloadedWithMove = true;
    }

    public boolean hasBeenPreloadedWithMove() {
        return preloadedWithMove;
    }
}
