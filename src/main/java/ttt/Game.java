package ttt;

public class Game {
    private static final int PLAYER_ONE_INDEX = 0;
    private static final int PLAYER_TWO_INDEX = 1;
    private final Board board;
    private Prompt gamePrompt;
    private Player[] players;

    public Game(Board board, Prompt gamePrompt, Player player1, Player player2) {
        this.board = board;
        this.gamePrompt = gamePrompt;
        players = new Player[]{player1, player2};
    }

    public void play() {
        int index = PLAYER_ONE_INDEX;
        while (board.hasFreeSpace()) {
            int nextMove = players[index].chooseNextMoveFrom(board);
            board.updateAt(nextMove, players[index].getSymbol());

            if (board.hasWinningCombination()) {
                gamePrompt.printWinningMessage();
                break;
            }

            index = toggle(index);
        }
    }

    private int toggle(int index) {
        return index == PLAYER_ONE_INDEX ? PLAYER_TWO_INDEX : PLAYER_ONE_INDEX;
    }
}