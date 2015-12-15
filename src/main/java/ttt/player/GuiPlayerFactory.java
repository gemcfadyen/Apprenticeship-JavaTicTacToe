package ttt.player;

import ttt.GameType;

import static ttt.GameType.*;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class GuiPlayerFactory implements PlayerFactory {
    private static final int THREE = 3;

    public GuiPlayerFactory() {
    }

    public Player[] createPlayers(GameType playerOption, int dimension) {
        if (HUMAN_VS_HUMAN == playerOption) {
            return humanVsHuman();
        } else if (HUMAN_VS_UNBEATABLE == playerOption) {
            return humanVsUnbeatable(dimension);
        } else if (UNBEATABLE_VS_HUMAN == playerOption) {
            return unbeatableVsHuman(dimension);
        }
        return humanVsHuman();
    }

    private Player[] humanVsHuman() {
        return new Player[]{new GuiHumanPlayer(X), new GuiHumanPlayer(O)};
    }

    private Player[] humanVsUnbeatable(int dimension) {
        return new Player[]{new GuiHumanPlayer(X),
                getUnbeatablePlayerFor(O, dimension)};
    }

    private Player[] unbeatableVsHuman(int dimension) {
        return new Player[]{getUnbeatablePlayerFor(X, dimension), new GuiHumanPlayer(O)};
    }

    private Player getUnbeatablePlayerFor(PlayerSymbol symbol, int dimension) {
        return dimension <= THREE
                ? new UnbeatablePlayer(symbol)
                : new DelayedUnbeatablePlayer(symbol, new UnbeatablePlayer(X));
    }
}
