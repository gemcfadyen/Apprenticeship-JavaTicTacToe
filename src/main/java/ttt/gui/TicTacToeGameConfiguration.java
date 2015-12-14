package ttt.gui;

import ttt.GameType;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;

import java.util.Arrays;
import java.util.List;

public class TicTacToeGameConfiguration implements GameConfiguration {
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;

    public TicTacToeGameConfiguration(BoardFactory boardFactory, PlayerFactory playerFactory) {
        this.boardFactory = boardFactory;
        this.playerFactory = playerFactory;
    }

    @Override
    public List<GameType> getGameTypes() {
        return Arrays.asList(GameType.values());
    }

    @Override
    public String getDimension(GameType gameType) {
        return String.valueOf(gameType.dimensionUpperBoundary());
    }

    @Override
    public GameRules initialiseGame(GameType gameType, int dimension) {
        return new TicTacToeRules(boardFactory, playerFactory, gameType, dimension);
    }


}
