package ttt.commandlineapp.prompt;

import ttt.game.GameType;
import ttt.game.PlayerSymbol;
import ttt.game.ReplayOption;
import ttt.game.board.Board;
import ttt.game.board.Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static ttt.game.PlayerSymbol.O;
import static ttt.game.PlayerSymbol.X;

public class PromptSpy implements Prompt {
    private final BufferedReader reader;
    private Board lastBoardPrinted;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;
    private int numberOfTimesXHasWon = 0;
    private int numberOfTimesOHasWon = 0;
    private int numberOfTimesPlayerIsRead = 0;
    private int numberOfTimesGameOptionsHaveBeenRead = 0;
    private int numberOfTimesBoardDimensionRead = 0;
    private int numberOfTimesBoardDimensionsAskedFor = 0;
    private int numberOfTimesGameOptionsAskedFor = 0;
    private int numberOfTimesReplayPresented = 0;

    public PromptSpy(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public int readBoardDimension(int lowerDimension, int largestDimension) {
        numberOfTimesBoardDimensionRead++;
        return Integer.valueOf(readInput());
    }

    @Override
    public GameType readGameType(List<GameType> gameTypes) {
        numberOfTimesGameOptionsHaveBeenRead++;
        return GameType.of(Integer.valueOf(readInput()));
    }

    @Override
    public ReplayOption readReplayOption() {
        numberOfTimesPlayerIsRead++;
        return ReplayOption.of(readInput());
    }

    @Override
    public int readNextMove(Board board) {
        return Integer.valueOf(readInput());
    }

    @Override
    public void presentGameTypes(List<GameType> allGameTypes) {
        numberOfTimesGameOptionsAskedFor++;
    }

    @Override
    public void presentGridDimensionsBetween(int lowerDimension, int highestDimension) {
        numberOfTimesBoardDimensionsAskedFor++;
    }

    @Override
    public void presentsBoard(Board board) {
        this.lastBoardPrinted = board;
    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {
        if (symbol == X) {
            numberOfTimesXHasWon++;
        }

        if (symbol == O) {
            numberOfTimesOHasWon++;
        }
        lastBoardPrinted = board;
    }

    @Override
    public void printsDrawMessage(Board board) {
        numberOfTimesDrawMessageHasBeenPrinted++;
        lastBoardPrinted = board;
    }

    @Override
    public void presentReplayOption() {
        numberOfTimesReplayPresented++;
    }

    private String readInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading in PromptSpy");
        }
    }

    public int getNumberOfTimesXHasWon() {
        return numberOfTimesXHasWon;
    }

    public int getNumberOfTimesOHasWon() {
        return numberOfTimesOHasWon;
    }

    public int getNumberOfTimesDrawMessageHasBeenPrinted() {
        return numberOfTimesDrawMessageHasBeenPrinted;
    }

    public String getLastBoardThatWasPrinted() {
        StringBuilder gridFormation = new StringBuilder();
        List<Line> rows = lastBoardPrinted.getRows();

        for (Line row : rows) {
            for (PlayerSymbol symbol : row.getSymbols()) {
                gridFormation.append(symbol);
            }
        }

        return gridFormation.toString();
    }

    public int getNumberOfTimesReplayOptionRead() {
        return numberOfTimesPlayerIsRead;
    }

    public int getNumberOfTimesPromptedForGameOption() {
        return numberOfTimesGameOptionsAskedFor;
    }

    public int getNumberOfTimesGameOptionsWereRead() {
        return numberOfTimesGameOptionsHaveBeenRead;
    }

    public int getNumberOfTimesDimensionsHaveBeenAskedFor() {
        return numberOfTimesBoardDimensionsAskedFor;
    }

    public int getNumberOfTimesBoardDimensionRead() {
        return numberOfTimesBoardDimensionRead;
    }

    public int getNumberOfTimesReplayPresented() {
        return numberOfTimesReplayPresented;
    }
}
