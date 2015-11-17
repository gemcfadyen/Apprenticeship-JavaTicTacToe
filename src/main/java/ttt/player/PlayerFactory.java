package ttt.player;

import ttt.GameType;
import ttt.ui.Prompt;

import static ttt.GameType.*;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class PlayerFactory {

    public Player[] createPlayers(GameType playerOption, Prompt prompt) {
        if (HUMAN_VS_HUMAN == playerOption) {
            return humanVsHuman(prompt);
        } else if (HUMAN_VS_UNBEATABLE == playerOption) {
            return humanVsUnbeatable(prompt);
        } else if (UNBEATABLE_VS_HUMAN == playerOption) {
            return unbeatableVsHuman(prompt);
        }
        return humanVsHuman(prompt);
    }

    private Player[] humanVsHuman(Prompt prompt) {
        return new Player[]{new HumanPlayer(prompt, X), new HumanPlayer(prompt, O)};
    }

    private Player[] humanVsUnbeatable(Prompt prompt) {
        return new Player[]{new HumanPlayer(prompt, X), new UnbeatablePlayer(O)};
    }

    private Player[] unbeatableVsHuman(Prompt prompt) {
        return new Player[]{new UnbeatablePlayer(X), new HumanPlayer(prompt, O)};
    }
}
