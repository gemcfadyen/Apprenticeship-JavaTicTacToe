package ttt.player.gameplan;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;

import java.util.ArrayList;
import java.util.List;

import static ttt.board.Board.BOARD_DIMENSION;
import static ttt.player.PlayerSymbol.*;

public class MinimaxGamePlan {

    public ValuedPosition minimax(Board board, PlayerSymbol maximisingSymbol, PlayerSymbol playerSymbol, int depth, boolean isMaxPlayer) {
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

    private ValuedPosition score(Board board, PlayerSymbol maximisingSymbol, int depth) {
        if (board.hasWinningCombination()) {
            if (board.getWinningSymbol() == maximisingSymbol) {
                return new ValuedPosition(10 + depth);
            } else {
                return new ValuedPosition(-10 - depth);
            }
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

    public ValuedPosition execute2(Board board, PlayerSymbol playerSymbol, int depth, boolean isMaxPlayer) {
        if (board.hasWinningCombination()) {
            return scoreForWinningBoard(board, playerSymbol, depth);
        }

        if (!board.hasFreeSpace()) {
            return new ValuedPosition(0);
        }

        ValuedPosition bestValuedPosition;
        if (isMaxPlayer) {
            bestValuedPosition = new ValuedPosition(-10);
            List<Integer> freeSpaces = board.getVacantPositions();

            for (int freeSpace : freeSpaces) {
                board.updateAt(freeSpace, playerSymbol);

                System.out.println(freeSpace + " MAX: \n" + print(board));

                ValuedPosition valuedPosition = execute2(board, opponent(playerSymbol), depth - 1, !isMaxPlayer);
                board.updateAt(freeSpace, VACANT);
                bestValuedPosition = max(bestValuedPosition, valuedPosition, freeSpace);
            }
        } else {
            bestValuedPosition = new ValuedPosition(10);
            List<Integer> freeSpaces = board.getVacantPositions();

            for (int freeSpace : freeSpaces) {
                board.updateAt(freeSpace, playerSymbol);
                System.out.println(freeSpace + " MIN: \n" + print(board));


                ValuedPosition valuedPosition = execute2(board, opponent(playerSymbol), depth - 1, !isMaxPlayer);
                board.updateAt(freeSpace, VACANT);
                bestValuedPosition = min(bestValuedPosition, valuedPosition, freeSpace);
            }

        }
        return bestValuedPosition;
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
        if (bestValuedPosition.getScore() <= valuedPosition.getScore()) {
            return new ValuedPosition(valuedPosition.getScore(), indexOfMove);
        }

        return bestValuedPosition;
    }

    private ValuedPosition min(ValuedPosition bestValuedPosition, ValuedPosition valuedPosition, int freeSpace) {
        if (bestValuedPosition.getScore() >= valuedPosition.getScore()) {
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

    private ValuedPosition scoreForWinningBoard(Board board, PlayerSymbol playerSymbol, int depth) {
        if (board.getWinningSymbol() == opponent(playerSymbol)) {
            return new ValuedPosition(10 + depth);
        } else {
            return new ValuedPosition(-10 + depth);
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