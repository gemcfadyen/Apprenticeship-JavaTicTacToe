package ttt;

import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.util.Random;

public class RandomPlayer extends Player {
    private static final int UPPER_BOUND = 9;
    private static final int LOWER_BOUND = 0;

    public RandomPlayer(Prompt prompt, PlayerSymbol playerSymbol) {
        super(prompt, playerSymbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int randomMove = generateRandomNumberFrom0To8();
        while (!(board.isWithinGridBoundary(randomMove) && board.isVacantAt(randomMove))) {
            System.out.println("Reprompting as " + randomMove + " is invalid");
            randomMove = generateRandomNumberFrom0To8();
        }
        System.out.println("Random number generated " + randomMove);
        return randomMove;
    }

    private int generateRandomNumberFrom0To8() {
        return new Random().nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
    }
}
