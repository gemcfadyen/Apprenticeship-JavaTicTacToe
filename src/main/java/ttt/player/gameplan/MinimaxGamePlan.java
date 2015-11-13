package ttt.player.gameplan;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.ArrayList;
import java.util.List;

import static ttt.board.Board.BOARD_DIMENSION;
import static ttt.player.PlayerSymbol.*;

public class MinimaxGamePlan {

    public ValuedPosition working(Board board, PlayerSymbol maximisingSymbol, PlayerSymbol playerSymbol, int depth, boolean isMaxPlayer) {
        List<ValuedPosition> valuedPositions = new ArrayList<>();
        if (!board.hasFreeSpace() || board.hasWinningCombination()) {
            return score(board, maximisingSymbol, depth);
        }

        List<Integer> vacantPositions = board.getVacantPositions();
        for (int vacantPosition : vacantPositions) {
            board.updateAt(vacantPosition, playerSymbol);
            ValuedPosition position = minimax(board, maximisingSymbol, opponent(playerSymbol), depth - 1, !isMaxPlayer);
            board.updateAt(vacantPosition, VACANT);
            valuedPositions.add(new ValuedPosition(position.getScore(), vacantPosition));
        }

        if (isMaxPlayer) {
            return max(valuedPositions);
        } else {
            return min(valuedPositions);
        }
    }

    public ValuedPosition minimax(Board board, PlayerSymbol maximisingSymbol, PlayerSymbol playerSymbol, int depth, boolean isMaxPlayer) {
        ValuedPosition bestPosition = isMaxPlayer ? new ValuedPosition(-100) : new ValuedPosition(100);

        List<Integer> vacantPositions = board.getVacantPositions();
        for (int vacantPosition : vacantPositions) {
            board.updateAt(vacantPosition, playerSymbol);

            ValuedPosition position;
            if (board.hasFreeSpace() && !board.hasWinningCombination()) {
                position = minimax(board, maximisingSymbol, opponent(playerSymbol), depth - 1, !isMaxPlayer);
            } else {
                position = score(board, maximisingSymbol, depth);

            }
            board.updateAt(vacantPosition, VACANT);
            if (isMaxPlayer) {
                bestPosition = max(bestPosition, position, vacantPosition);
            } else {
                bestPosition = min(bestPosition, position, vacantPosition);
            }
        }
        return bestPosition;

    }

    private ValuedPosition score(Board board, PlayerSymbol maximisingSymbol, int depth) {
        if (board.getWinningSymbol() == maximisingSymbol) {
            return new ValuedPosition(10 + depth);
        } else if (board.getWinningSymbol() == opponent(maximisingSymbol)) {
            return new ValuedPosition(-10 - depth);
        }
        return new ValuedPosition(0);
    }

    private ValuedPosition min(List<ValuedPosition> valuedPositions) {
        int minValue = 100;
        int bestPosition = -1;
        for (ValuedPosition position : valuedPositions) {
            if (minValue > position.getScore()) {
                minValue = position.getScore();
                bestPosition = position.getMove();
            }
        }
        return new ValuedPosition(minValue, bestPosition);
    }

    private ValuedPosition max(List<ValuedPosition> valuedPositions) {
        int maxValue = -100;
        int bestPosition = -1;
        for (ValuedPosition position : valuedPositions) {
            if (maxValue < position.getScore()) {
                maxValue = position.getScore();
                bestPosition = position.getMove();
            }
        }
        return new ValuedPosition(maxValue, bestPosition);
    }

    public ValuedPosition minimax_notworking(Board board, PlayerSymbol unused, PlayerSymbol playerSymbol, int depth, boolean isMaxPlayer) {

        ValuedPosition bestValuedPosition = isMaxPlayer ? new ValuedPosition(-100) : new ValuedPosition(100);
        ;
        List<Integer> freeSpaces = board.getVacantPositions();

        for (int freeSpace : freeSpaces) {
            board.updateAt(freeSpace, playerSymbol);

            System.out.println(freeSpace + "\n" + print(board));
            ValuedPosition valuedPosition;
            if (notFinished(board)) {
                valuedPosition = minimax(board, VACANT, opponent(playerSymbol), depth - 1, !isMaxPlayer);
            } else {
                valuedPosition = scoreForWinningBoard(board, playerSymbol, depth, freeSpace);
            }


            if (isMaxPlayer) {
                bestValuedPosition = max(bestValuedPosition, valuedPosition, freeSpace);
            } else {
                bestValuedPosition = min(bestValuedPosition, valuedPosition, freeSpace);
            }
            board.updateAt(freeSpace, VACANT);

        }

        return bestValuedPosition;
    }

    private boolean notFinished(Board board) {
        return board.hasFreeSpace() && !board.hasWinningCombination();
    }

    private String print(Board board) {
        String boardForDisplay = "";

        Line[] rows = board.getRows();
        int offset = 0;
        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
                if (symbol == VACANT) {
                    boardForDisplay += " -";
                } else {
                    boardForDisplay +=
                            " "
                                    + symbol;
                }
                if (endOfRow(offset)) {
                    boardForDisplay += "\n";
                }
                offset++;
            }
        }

        return boardForDisplay;
    }

    private boolean endOfRow(int index) {
        return (index + 1) % BOARD_DIMENSION == 0;
    }

    private ValuedPosition max(ValuedPosition bestValuedPosition, ValuedPosition valuedPosition, int indexOfMove) {
        if (bestValuedPosition.getScore() < valuedPosition.getScore()) {
            return new ValuedPosition(valuedPosition.getScore(), indexOfMove);
        }

        return bestValuedPosition;
    }

    private ValuedPosition min(ValuedPosition bestValuedPosition, ValuedPosition valuedPosition, int freeSpace) {
        if (bestValuedPosition.getScore() > valuedPosition.getScore()) {
            return new ValuedPosition(valuedPosition.getScore(), freeSpace);
        }

        return bestValuedPosition;
    }

    private PlayerSymbol opponent(PlayerSymbol maxPlayerSymbol) {
        if (maxPlayerSymbol.equals(X)) {
            return O;
        } else {
            return X;
        }
    }

    private ValuedPosition scoreForWinningBoard(Board board, PlayerSymbol playerSymbol, int depth, int position) {
        if (board.getWinningSymbol() == playerSymbol) {
            return new ValuedPosition(10 + depth, position);
        } else if (board.getWinningSymbol() == opponent(playerSymbol)) {
            return new ValuedPosition(-10 - depth, position);
        } else {
            return new ValuedPosition(0, position);
        }
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