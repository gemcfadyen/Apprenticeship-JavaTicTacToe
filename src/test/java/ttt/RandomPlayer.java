package ttt;

import ttt.board.Board;
import ttt.player.Player;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.util.Random;

public class RandomPlayer extends Player {
    public RandomPlayer(PlayerSymbol playerSymbol, Prompt prompt) {
        super(playerSymbol, prompt);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int dimension = board.getRows().length;
        int randomMove = generateRandomNumber(dimension);
        while (!(board.isWithinGridBoundary(randomMove) && board.isVacantAt(randomMove))) {
            System.out.println("Reprompting as " + randomMove + " is invalid");
            randomMove = generateRandomNumber(dimension);
        }
        System.out.println("Random number generated " + randomMove);
        return randomMove;
    }

    private int generateRandomNumber(int dimension) {
        int lowerBoundary = 0;
        int upperBoundary = dimension * dimension;
        return new Random().nextInt(upperBoundary - lowerBoundary) + lowerBoundary;
    }
}
