package ttt.player;

import ttt.board.Board;
import ttt.ui.Prompt;

import java.util.List;

import static ttt.player.PlayerSymbol.*;

public class UnbeatablePlayer extends Player {
    public UnbeatablePlayer(Prompt prompt, PlayerSymbol symbol) {
        super(prompt, symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        int remainingDepthToExplore = board.getVacantPositions().size();
        ValuedPosition bestMove = minimax(board, remainingDepthToExplore, getSymbol(), true);
        return bestMove.getMove();
    }

    private ValuedPosition minimax(Board board, int remainingDepth, PlayerSymbol currentPlayer, boolean isMaxPlayer) {
        ValuedPosition bestPosition = isMaxPlayer ? new ValuedPosition(-100) : new ValuedPosition(100);

        List<Integer> vacantPositions = board.getVacantPositions();
        for (int vacantPosition : vacantPositions) {
            board.updateAt(vacantPosition, currentPlayer);

            ValuedPosition position;
            if (gameIsInProgress(board)) {
                position = minimax(board, remainingDepth - 1, PlayerSymbol.opponent(currentPlayer), !isMaxPlayer);
            } else {
                position = score(board, remainingDepth);
            }
            board.updateAt(vacantPosition, VACANT);

            bestPosition = getBestPositionFor(isMaxPlayer, bestPosition, position, vacantPosition);
        }
        return bestPosition;
    }

    private boolean gameIsInProgress(Board board) {
        return board.hasFreeSpace() && !board.hasWinningCombination();
    }

    private ValuedPosition score(Board board, int remainingDepth) {
        if (board.getWinningSymbol() == getSymbol()) {
            return new ValuedPosition(10 + remainingDepth);
        } else if (board.getWinningSymbol() == PlayerSymbol.opponent(getSymbol())) {
            return new ValuedPosition(-10 - remainingDepth);
        }
        return new ValuedPosition(0);
    }

    private ValuedPosition getBestPositionFor(boolean isMaxPlayer, ValuedPosition bestPosition, ValuedPosition position, int vacantPosition) {
        if (isMaxPlayer) {
            return max(bestPosition, position, vacantPosition);
        } else {
            return min(bestPosition, position, vacantPosition);
        }
    }

    private ValuedPosition max(ValuedPosition bestValuedPosition, ValuedPosition valuedPosition, int indexOfMove) {
        if (bestValuedPosition.getScore() < valuedPosition.getScore()) {
            return new ValuedPosition(valuedPosition.getScore(), indexOfMove);
        }

        return bestValuedPosition;
    }

    private ValuedPosition min(ValuedPosition bestValuedPosition, ValuedPosition valuedPosition, int indexOfMove) {
        if (bestValuedPosition.getScore() > valuedPosition.getScore()) {
            return new ValuedPosition(valuedPosition.getScore(), indexOfMove);
        }

        return bestValuedPosition;
    }
}

class ValuedPosition {
    private int score;
    private int move;

    public ValuedPosition(int score) {
        this.score = score;
        this.move = -1;
    }

    public ValuedPosition(int score, int indexOfMove) {
        this.score = score;
        this.move = indexOfMove;
    }

    public int getScore() {
        return score;
    }

    public int getMove() {
        return move;
    }
}