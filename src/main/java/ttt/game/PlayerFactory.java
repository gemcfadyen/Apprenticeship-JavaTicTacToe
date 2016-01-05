package ttt.game;

import ttt.game.players.DelayedUnbeatablePlayer;
import ttt.game.players.UnbeatablePlayer;

import static ttt.game.GameType.*;
import static ttt.game.PlayerSymbol.O;
import static ttt.game.PlayerSymbol.X;

public abstract class PlayerFactory {
    private static final int THREE = 3;
    private ReadPrompt playerPrompt;

    public PlayerFactory(ReadPrompt playerPrompt) {
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

    private Player[] humanVsHuman(ReadPrompt prompt) {
        return new Player[]{createHumanPlayer(X, prompt), createHumanPlayer(O, prompt)};
    }

    private Player[] humanVsUnbeatable(ReadPrompt prompt, int dimension) {
        return new Player[]{createHumanPlayer(X, prompt),
                getUnbeatablePlayerFor(O, dimension)};
    }

    private Player[] unbeatableVsHuman(ReadPrompt prompt, int dimension) {
        return new Player[]{getUnbeatablePlayerFor(X, dimension), createHumanPlayer(O, prompt)};
    }

    private Player getUnbeatablePlayerFor(PlayerSymbol symbol, int dimension) {
        return dimension <= THREE
                ? new UnbeatablePlayer(symbol)
                : new DelayedUnbeatablePlayer(symbol, new UnbeatablePlayer(X));
    }

    public abstract Player createHumanPlayer(PlayerSymbol symbol, ReadPrompt prompt);
}
