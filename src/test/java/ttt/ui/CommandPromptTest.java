package ttt.ui;

import org.junit.Before;
import org.junit.Test;
import ttt.GameType;
import ttt.ReaderStubWhichThrowsExceptionOnRead;
import ttt.ReplayOption;
import ttt.WriterStubWhichThrowsExceptionOnWrite;
import ttt.board.Board;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.GameType.HUMAN_VS_UNBEATABLE;
import static ttt.ReplayOption.Y;
import static ttt.player.PlayerSymbol.*;


public class CommandPromptTest {
    private StringReader defaultReader;
    private StringWriter writer;
    private BoardDisplaySpy boardFormatterSpy;
    private TextFormatterSpy textFormatterSpy;

    @Before
    public void setup() {
        defaultReader = new StringReader("");
        writer = new StringWriter();
        textFormatterSpy = new TextFormatterSpy();
        boardFormatterSpy = new BoardDisplaySpy();
    }

    @Test
    public void askUserForBoardDimension() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.presentGridDimensionsBetween(1, 5);

        assertThat(textFormatterSpy.hasPresentedDimensions(), is(true));
    }

    @Test
    public void readsDimensionFromCommandLine() {
        Prompt prompt = new CommandPrompt(new StringReader("3\n"), writer, boardFormatterSpy, textFormatterSpy);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(3));
    }

    @Test
    public void repromptsUserWhenNonNumericEnteredForBoardDimension() {
        Prompt prompt = new CommandPrompt(new StringReader("z\n4\n"), writer, boardFormatterSpy, textFormatterSpy);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(textFormatterSpy.numberOfBoardDimensionPrompts(), is(1));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
        assertThat(textFormatterSpy.numberOfClearMessages(), is(3));
    }


    @Test
    public void repromptsUserWhenInvalidDimensionEnteredForGameType() {
        Prompt prompt = new CommandPrompt(new StringReader("100\n4\n"), writer, boardFormatterSpy, textFormatterSpy);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_UNBEATABLE.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(textFormatterSpy.numberOfBoardDimensionPrompts(), is(1));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
        assertThat(textFormatterSpy.numberOfClearMessages(), is(3));
    }

    @Test
    public void printsBoard() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);
        prompt.presentsBoard(new Board(3));

        assertThat(boardFormatterSpy.numberOfTimesBoardDisplayed(), is(1));
    }

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);
        prompt.readNextMove(new Board(3));

        assertThat(boardFormatterSpy.numberOfTimesBoardDisplayed(), is(1));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readNextMove(new Board(3));

        assertThat(textFormatterSpy.numberOfTakeMoveMessage(), is(2));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
        assertThat(textFormatterSpy.numberOfClearMessages(), is(3));
        assertThat(boardFormatterSpy.numberOfTimesBoardDisplayed(), is(2));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readNextMove(new Board(3));

        assertThat(textFormatterSpy.numberOfClearMessages(), is(2));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));
    }

    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        Prompt prompt = new CommandPrompt(new StringReader("a\nz\n1\n"), writer, boardFormatterSpy, textFormatterSpy);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));

        assertThat(textFormatterSpy.numberOfTakeMoveMessage(), is(3));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(2));
        assertThat(textFormatterSpy.numberOfClearMessages(), is(4));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        Prompt prompt = new CommandPrompt(new StringReader("100\n-100\n1\n"), writer, boardFormatterSpy, textFormatterSpy);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));

        assertThat(textFormatterSpy.numberOfTakeMoveMessage(), is(3));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(2));
    }

    @Test
    public void repromptsWhenMoveIsAlreadyOccupied() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n2\n"), writer, boardFormatterSpy, textFormatterSpy);

        int nextMove = prompt.readNextMove(new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT));

        assertThat(nextMove, equalTo(1));
        assertThat(textFormatterSpy.numberOfTakeMoveMessage(), is(2));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
    }

    @Test
    public void clearsScreenBeforePromptingForReplay() {
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readReplayOption();

        assertThat(textFormatterSpy.numberOfClearMessages(), is(2));
    }

    @Test
    public void presentsReplayOption() {
        Prompt prompt = new CommandPrompt(defaultReader, writer, boardFormatterSpy, textFormatterSpy);

        prompt.presentReplayOption();

        assertThat(textFormatterSpy.numberOfReplayMessages(), is(1));
    }


    @Test
    public void readsReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer, boardFormatterSpy, textFormatterSpy);

        ReplayOption replayOption = prompt.readReplayOption();

        assertThat(replayOption, is(Y));
    }

    @Test
    public void readsLowerCaseReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("y\n"), writer, boardFormatterSpy, textFormatterSpy);

        ReplayOption replayOption = prompt.readReplayOption();

        assertThat(replayOption, is(Y));
    }


    @Test
    public void clearsScreenWhenReadingReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readReplayOption();

        assertThat(textFormatterSpy.numberOfClearMessages(), is(2));
    }

    @Test
    public void clearsScreenAndRepromptsWhenReplayOptionIsInvalid() {
        Prompt prompt = new CommandPrompt(new StringReader("A\nN\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readReplayOption();

        assertThat(textFormatterSpy.numberOfClearMessages(), is(3));
        assertThat(textFormatterSpy.numberOfReplayMessages(), is(1));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
    }

    @Test
    public void presentsGameTypes() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        List<GameType> allGameTypes = Arrays.asList(GameType.values());
        prompt.presentGameTypes(allGameTypes);

        assertThat(textFormatterSpy.numberOfClearMessages(), is(1));
        assertThat(textFormatterSpy.numberOfGameTypesMessages(), is(1));
    }

    @Test
    public void readsGameType() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }

    @Test
    public void clearsScreenWhenReadingGameTypeOption() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, boardFormatterSpy, textFormatterSpy);

        prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(textFormatterSpy.numberOfClearMessages(), is(2));
    }

    @Test
    public void clearsScreenAndRepromptsGameTypeWhenAlphaCharacterEntered() {
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, boardFormatterSpy, textFormatterSpy);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
        assertThat(textFormatterSpy.numberOfGameTypesMessages(), is(1));
        assertThat(textFormatterSpy.numberOfClearMessages(), is(3));
    }

    @Test
    public void repromptsWhenInvalidGameTypeEntered() {
        Prompt prompt = new CommandPrompt(new StringReader("7\n1\n"), writer, boardFormatterSpy, textFormatterSpy);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));
        assertThat(textFormatterSpy.numberOfValidationErrors(), is(1));
        assertThat(textFormatterSpy.numberOfGameTypesMessages(), is(1));
    }

    @Test
    public void printsWinner() {
        CommandPrompt prompt = new CommandPrompt(defaultReader, writer, boardFormatterSpy, textFormatterSpy);
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        prompt.printsWinningMessage(board, X);

        assertThat(boardFormatterSpy.numberOfTimesBoardDisplayed(), is(1));
        assertThat(textFormatterSpy.numberOfWinningMessages(), is(1));
    }

    @Test
    public void printsDrawMessage() {
        CommandPrompt prompt = new CommandPrompt(defaultReader, writer, boardFormatterSpy, textFormatterSpy);
        Board board = new Board(
                X, O, X,
                O, X, X,
                O, X, O
        );

        prompt.printsDrawMessage(board);

        assertThat(boardFormatterSpy.numberOfTimesBoardDisplayed(), is(1));
        assertThat(textFormatterSpy.numberOfDrawMessages(), is(1));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        new CommandPrompt(defaultReader, writer, boardFormatterSpy, textFormatterSpy);
        assertThat(textFormatterSpy.numberOfClearMessages(), is(1));
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(defaultReader, writerWhichThrowsIOException, boardFormatterSpy, textFormatterSpy);
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        promptWhichThrowsExceptionOnWrite.printsWinningMessage(board, X);
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, writer, boardFormatterSpy, textFormatterSpy);
        promptWhichHasExceptionOnRead.readReplayOption();
    }

}
