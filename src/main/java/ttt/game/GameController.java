package ttt.game;

public interface GameController {
    void presentGameTypes();
    void presentBoardDimensionsFor(GameType gameType);
    void startGame(int dimension);
    void takeMove(int position);
    Player getCurrentPlayer();
}
