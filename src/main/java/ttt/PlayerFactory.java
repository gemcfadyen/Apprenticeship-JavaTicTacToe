package ttt;

import static ttt.GameType.*;
import static ttt.PlayerSymbol.O;
import static ttt.PlayerSymbol.X;

public class PlayerFactory {

    public Player[] createPlayers(int playerOption, Prompt prompt) {
        if (HUMAN_VS_HUMAN.numericRepresentation() == playerOption) {
            return humanVsHuman(prompt);
        }
        return humanVsHuman(prompt);
    }

    private Player[] humanVsHuman(Prompt prompt) {
        return new Player[]{new HumanPlayer(prompt, X), new HumanPlayer(prompt, O)};
    }
}
