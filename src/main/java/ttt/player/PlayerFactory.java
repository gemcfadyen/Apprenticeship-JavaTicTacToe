package ttt.player;

import ttt.ui.Prompt;

import static ttt.GameType.HUMAN_VS_HUMAN;

public class PlayerFactory {

    public Player[] createPlayers(int playerOption, Prompt prompt) {
        if (HUMAN_VS_HUMAN.numericRepresentation() == playerOption) {
            return humanVsHuman(prompt);
        }
        return humanVsHuman(prompt);
    }

    private Player[] humanVsHuman(Prompt prompt) {
        return new Player[]{new HumanPlayer(prompt, PlayerSymbol.X), new HumanPlayer(prompt, PlayerSymbol.O)};
    }
}
