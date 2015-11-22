package ttt.player;

import ttt.GameType;
import ttt.ui.Prompt;

import static ttt.GameType.*;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class PlayerFactory {
    private static final int THREE = 3;

    public Player[] createPlayers(GameType playerOption, Prompt prompt, int dimension) {
        if (HUMAN_VS_HUMAN == playerOption) {
            return humanVsHuman(prompt);
        } else if (HUMAN_VS_UNBEATABLE == playerOption) {
            return humanVsUnbeatable(prompt, dimension);
        } else if (UNBEATABLE_VS_HUMAN == playerOption) {
            return unbeatableVsHuman(prompt, dimension);
        }
        return humanVsHuman(prompt);
    }

    private Player[] humanVsHuman(Prompt prompt) {
        return new Player[]{new HumanPlayer(X, prompt), new HumanPlayer(O, prompt)};
    }

    private Player[] humanVsUnbeatable(Prompt prompt, int dimension) {
        return new Player[]{new HumanPlayer(X, prompt),
                getUnbeatablePlayerFor(O, dimension)};
    }

    private Player[] unbeatableVsHuman(Prompt prompt, int dimension) {
        return new Player[]{getUnbeatablePlayerFor(X, dimension), new HumanPlayer(O, prompt)};
    }

    private Player getUnbeatablePlayerFor(PlayerSymbol symbol, int dimension) {
        return dimension <= THREE
                ? new UnbeatablePlayer(symbol)
                : new DelayedUnbeatablePlayer(symbol, new UnbeatablePlayer(X));
    }
}
