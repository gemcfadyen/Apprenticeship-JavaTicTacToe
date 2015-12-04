package ttt.gui;

import ttt.GameType;
import ttt.player.PlayerSymbol;

public class TicTacToeRulesSpy implements GameRules {
    private boolean hasGotGameTypes = false;
    private boolean hasGotBoardDimensions = false;

    @Override
    public void playMoveAt(String move) {

    }

    @Override
    public PlayerSymbol getCurrentPlayer() {
        return null;
    }

    @Override
    public boolean hasWinner() {
        return false;
    }

    @Override
    public void togglePlayer() {

    }

    @Override
    public GameType getGameTypes() {
        hasGotGameTypes = true;
        return GameType.HUMAN_VS_HUMAN;
    }

    @Override
    public String getDimension(GameType gameType) {
        hasGotBoardDimensions = true;
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

    public boolean hasObtainedBoardDimensions() {
        return hasGotBoardDimensions;
    }
}
