package ttt.gui;

import ttt.GameType;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;

public class GameRulesFactory {
    private BoardFactory boardFactory;
    private PlayerFactory playerFactory;

    public GameRulesFactory(BoardFactory boardFactory, PlayerFactory playerFactory) {
        this.boardFactory = boardFactory;
        this.playerFactory = playerFactory;
    }

    public void withGameType(GameType gameType) {
       // this.gameType = gameType;
    }
}
