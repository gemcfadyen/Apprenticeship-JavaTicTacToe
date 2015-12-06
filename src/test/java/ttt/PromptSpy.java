package ttt;

import ttt.board.Board;
import ttt.board.Line;
import ttt.player.PlayerSymbol;
import ttt.ui.Prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class PromptSpy implements Prompt {
    private final BufferedReader reader;
    private Board lastBoardPrinted;
    private int numberOfTimesDrawMessageHasBeenPrinted = 0;
    private int numberOfTimesXHasWon = 0;
    private int numberOfTimesOHasWon = 0;
    private int numberOfTimesPlayerIsReprompted = 0;
    private int numberOfTimesGameOptionsHaveBeenRead = 0;
    private int numberOfTimesBoardDimensionRead = 0;
    private int numberOfTimesBoardDimensionsAskedFor = 0;
    private int numberOfTimesGameOptionsAskedFor = 0;

    public PromptSpy(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public int readBoardDimension(GameType gameType) {
        numberOfTimesBoardDimensionRead++;
        return Integer.valueOf(readInput());
    }

    @Override
    public GameType readGameType() {
        numberOfTimesGameOptionsHaveBeenRead++;
        return GameType.of(Integer.valueOf(readInput()));
    }

    @Override
    public ReplayOption getReplayOption() {
        numberOfTimesPlayerIsReprompted++;
        return ReplayOption.of(readInput());
    }

    @Override
    public int getNextMove(Board board) {
        return Integer.valueOf(readInput());
    }

    @Override
    public void print(Board board) {
        this.lastBoardPrinted = board;
    }

    @Override
    public void presentGameTypes(List<GameType> allGameTypes) {
        numberOfTimesGameOptionsAskedFor++;
    }

    @Override
    public void presentGridDimensionsUpTo(String dimension) {

    }

    @Override
    public void presentsBoard(Board board) {

    }

    @Override
    public void printsWinningMessage(Board board, PlayerSymbol symbol) {

    }

    @Override
    public void printsDrawMessage(Board board) {

    }

    @Override
    public void presentBoardDimensionsFor(GameType gameType) {
        numberOfTimesBoardDimensionsAskedFor++;
    }

    @Override
    public void printWinningMessageFor(PlayerSymbol symbol) {
        if (symbol == X) {
            numberOfTimesXHasWon++;
        }

        if (symbol == O) {
            numberOfTimesOHasWon++;
        }
    }

    @Override
    public void printDrawMessage() {
        numberOfTimesDrawMessageHasBeenPrinted++;
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

    public int getNumberOfTimesPlayerIsPromptedToPlayAgain() {
        return numberOfTimesPlayerIsReprompted;
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
}
