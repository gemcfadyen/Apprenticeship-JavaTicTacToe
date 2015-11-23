package ttt.player;

import ttt.board.Board;

import java.util.List;

import static ttt.player.PlayerSymbol.VACANT;
import static ttt.player.PlayerSymbol.opponent;

public class UnbeatablePlayer extends Player {
    private static final int ALPHA = -100;
    private static final int BETA = 100;

    public UnbeatablePlayer(PlayerSymbol symbol) {
        super(symbol);
    }

    @Override
    public int chooseNextMoveFrom(Board board) {
        ValuedPosition bestMove = alphaBetaMinimax(
                board,
                board.getRows().size() * 2,
                getSymbol(),
                true,
                ALPHA,
                BETA);
        return bestMove.getMove();
    }

    private ValuedPosition alphaBetaMinimax(Board board, int remainingDepth, PlayerSymbol currentPlayer, boolean isMaxPlayer, int alpha, int beta) {
        ValuedPosition bestPosition = isMaxPlayer
                ? new MaxPlayerInitialScore()
                : new MinPlayerInitialScore();

        if (gameOver(board) || hasZero(remainingDepth)) {
            return score(board, remainingDepth);
        }

        List<Integer> vacantPositions = board.getVacantPositions();
        for (int vacantPosition : vacantPositions) {
            board.updateAt(vacantPosition, currentPlayer);

            ValuedPosition position = alphaBetaMinimax(board, remainingDepth - 1, opponent(currentPlayer), !isMaxPlayer, alpha, beta);

            revertMove(board, vacantPosition);
            bestPosition = getPlayersBestPosition(isMaxPlayer, bestPosition, position, vacantPosition);
            alpha = recalculateAlpha(isMaxPlayer, bestPosition, alpha);
            beta = recalculateBeta(isMaxPlayer, bestPosition, beta);

            if (pruneBranches(alpha, beta)) {
                break;
            }
        }
        return bestPosition;
    }

    private boolean gameOver(Board board) {
        return !board.hasFreeSpace() || board.hasWinningCombination();
    }

    private boolean hasZero(int remainingDepth) {
        return remainingDepth == 0;
    }

    private ValuedPosition score(Board board, int remainingDepth) {
        if (board.getWinningSymbol() == getSymbol()) {
            return new MaxPlayerWin(remainingDepth);
        } else if (board.getWinningSymbol() == opponent(getSymbol())) {
            return new MinPlayerWin(remainingDepth);
        }
        return new Draw();
    }

    private void revertMove(Board board, int vacantPosition) {
        board.updateAt(vacantPosition, VACANT);
    }

    private ValuedPosition getPlayersBestPosition(boolean isMaxPlayer, ValuedPosition bestPosition, ValuedPosition position, int vacantPosition) {
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

    private int recalculateAlpha(boolean isMaxPlayer, ValuedPosition bestPosition, int alpha) {
        if (isMaxPlayer) {
            if (bestPosition.getScore() > alpha) {
                return bestPosition.getScore();
            }
        }
        return alpha;
    }

    private int recalculateBeta(boolean isMaxPlayer, ValuedPosition bestPosition, int beta) {
        if (!isMaxPlayer) {
            if (bestPosition.getScore() < beta) {
                return bestPosition.getScore();
            }
        }
        return beta;
    }

    private boolean pruneBranches(int alpha, int beta) {
        return alpha >= beta;
    }

    private static class ValuedPosition {
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

    private static class Draw extends ValuedPosition {
        private static final int DRAW_SCORE = 0;

        public Draw() {
            super(DRAW_SCORE);
        }
    }

    private static class MaxPlayerInitialScore extends ValuedPosition {
        private static final int MAX_PLAYER_INITIAL_SCORE = -100;
        public MaxPlayerInitialScore() {
            super(MAX_PLAYER_INITIAL_SCORE);
        }
    }

    private static class MinPlayerInitialScore extends ValuedPosition {
        private static final int MIN_PLAYER_INITIAL_SCORE = 100;
        public MinPlayerInitialScore() {
            super(MIN_PLAYER_INITIAL_SCORE);
        }
    }

    private static class MaxPlayerWin extends ValuedPosition {
        private static final int MAX_PLAYER_WIN_SCORE = 10;

        public MaxPlayerWin(int depth) {
            super(MAX_PLAYER_WIN_SCORE + depth);
        }
    }

    private static class MinPlayerWin extends ValuedPosition {
        private static final int MIN_PLAYER_WIN_SCORE = -10;

        public MinPlayerWin(int depth) {
            super(MIN_PLAYER_WIN_SCORE - depth);
        }
    }
}
