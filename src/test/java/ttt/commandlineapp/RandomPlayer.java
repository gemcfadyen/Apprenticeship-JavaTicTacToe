package ttt.commandlineapp;

import ttt.game.Board;
import ttt.game.Player;
import ttt.game.PlayerSymbol;

import java.util.Random;

public class RandomPlayer extends Player {
    public RandomPlayer(PlayerSymbol playerSymbol, Prompt prompt) {
        super(playerSymbol, prompt);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int dimension = board.getRows().size();
        int randomMove = generateRandomNumber(dimension);
        while (!(board.isWithinGridBoundary(randomMove) && board.isVacantAt(randomMove))) {
            randomMove = generateRandomNumber(dimension);
        }
        return randomMove;
    }

    private int generateRandomNumber(int dimension) {
        int lowerBoundary = 0;
        int upperBoundary = dimension * dimension;
        return new Random().nextInt(upperBoundary - lowerBoundary) + lowerBoundary;
    }
}
