package ttt.player;

import ttt.GameType;
import ttt.ui.Prompt;

import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.GameType.UNBEATABLE_VS_HUMAN;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public abstract class PlayerFactory {
    private static final int THREE = 3;
    private Prompt playerPrompt;

    public PlayerFactory(Prompt playerPrompt) {
        this.playerPrompt = playerPrompt;
    }

    public Player[] createPlayers(GameType playerOption, int dimension) {
        if (HUMAN_VS_HUMAN == playerOption) {
            return humanVsHuman(playerPrompt);
        } else if (HUMAN_VS_UNBEATABLE == playerOption) {
            return humanVsUnbeatable(playerPrompt, dimension);
        } else if (UNBEATABLE_VS_HUMAN == playerOption) {
            return unbeatableVsHuman(playerPrompt, dimension);
        }
        return humanVsHuman(playerPrompt);
    }

    private Player[] humanVsHuman(Prompt prompt) {
        return new Player[]{createHumanPlayer(X, prompt), createHumanPlayer(O, prompt)};
    }

    private Player[] humanVsUnbeatable(Prompt prompt, int dimension) {
        return new Player[]{createHumanPlayer(X, prompt),
                getUnbeatablePlayerFor(O, dimension)};
    }

    private Player[] unbeatableVsHuman(Prompt prompt, int dimension) {
        return new Player[]{getUnbeatablePlayerFor(X, dimension), createHumanPlayer(O, prompt)};
    }

    private Player getUnbeatablePlayerFor(PlayerSymbol symbol, int dimension) {
        return dimension <= THREE
                ? new UnbeatablePlayer(symbol)
                : new DelayedUnbeatablePlayer(symbol, new UnbeatablePlayer(X));
    }

    public abstract Player createHumanPlayer(PlayerSymbol symbol, Prompt prompt);
}
