package ttt.gui;

import ttt.GameType;
import ttt.board.Board;
import ttt.player.PlayerSymbol;

public class TicTacToeRulesSpy implements GameRules {
    private boolean hasGotGameTypes = false;
    private boolean hasGotBoardDimensions = false;
    private boolean hasInitialisedGame = false;
    private boolean hasStoredGameType = false;

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
    public void initialiseGame(String dimension) {
        hasInitialisedGame = true;
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

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public void storeGameType(GameType gameType) {
        hasStoredGameType = true;
    }

    public boolean hasObtainedGameTypes() {
        return hasGotGameTypes;
    }

    public boolean hasObtainedBoardDimensions() {
        return hasGotBoardDimensions;
    }

    public boolean hasInitialisedGame() {
        return hasInitialisedGame;
    }

    public boolean hasStoredGameType() {
        return hasStoredGameType;
    }
}
