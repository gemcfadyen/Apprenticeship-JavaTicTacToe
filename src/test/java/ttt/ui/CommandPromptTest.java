package ttt.ui;

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
    private static final String CLEAR_SCREEN_ANSI_CHARACTERS = "\033[H\033[2J";
    private final StringReader defaultReader = new StringReader("");
    private StringWriter writer = new StringWriter();
    private TextPresenter textPresenter = new StandardTextPresenter();
    private PlainBoard plainFormatter = new PlainBoard();

    @Test
    public void askUserForBoardDimension() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        prompt.presentGridDimensionsBetween(1, 5);

        assertThat(writer.toString().contains("Please enter the dimension of the board you would like to use [1 to 5]\n"), is(true));
    }

    @Test
    public void readsDimensionFromCommandLine() {
        Prompt prompt = new CommandPrompt(new StringReader("3\n"), writer, plainFormatter, textPresenter);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(3));
    }

    @Test
    public void repromptsUserWhenNonNumericEnteredForBoardDimension() {
        Prompt prompt = new CommandPrompt(new StringReader("z\n4\n"), writer, plainFormatter, textPresenter);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                "[z] is not a valid integer\n\n"
                        + "Please enter the dimension of the board you would like to use [2 to 5]\n\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }


    @Test
    public void repromptsUserWhenInvalidDimensionEnteredForGameType() {
        Prompt prompt = new CommandPrompt(new StringReader("100\n4\n"), writer, plainFormatter, textPresenter);

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionLowerBoundary(), HUMAN_VS_UNBEATABLE.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                "[100] is outside of the range 1 to 4\n\n"
                        + "Please enter the dimension of the board you would like to use [2 to 4]\n\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void printsBoard() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);
        prompt.presentsBoard(new Board(3));

        assertThat(writer.toString().contains("---------"), is(true));
    }

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);
        prompt.readNextMove(new Board(3));

        assertThat(writer.toString().contains("---------"), is(true));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, plainFormatter, textPresenter);

        prompt.readNextMove(new Board(3));

        assertThat(writer.toString().endsWith("\n" +
                CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n"
                + "---------" + "\n\n"
                + "[a] is not a valid integer\n\n"
                + "Please enter the index for your next move"
                + "\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        prompt.readNextMove(new Board(3));

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));
    }

    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        Prompt prompt = new CommandPrompt(new StringReader("a\nz\n1\n"), writer, plainFormatter, textPresenter);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));
        assertThat(writer.toString().contains("[a] is not a valid integer\n\nPlease enter the index for your next move"), is(true));
        assertThat(writer.toString().contains(
                "[z] is not a valid integer\n\nPlease enter the index for your next move"), is(true));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        Prompt prompt = new CommandPrompt(new StringReader("100\n-100\n1\n"), writer, plainFormatter, textPresenter);

        assertThat(prompt.readNextMove(new Board(3)), equalTo(0));

        assertThat(writer.toString().contains("[100] is outside of the grid boundary\n\nPlease enter the index for your next move"), is(true));
        assertThat(writer.toString().contains("[-100] is outside of the grid boundary\n\nPlease enter the index for your next move"), is(true));
    }

    @Test
    public void repromptsWhenMoveIsAlreadyOccupied() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n2\n"), writer, plainFormatter, textPresenter);

        assertThat(prompt.readNextMove(new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT)), equalTo(1));
        assertThat(writer.toString().contains("[1] is already occupied\n\nPlease enter the index for your next move"), is(true));
    }

    @Test
    public void clearsScreenBeforePromptingForReplay() {
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer, plainFormatter, textPresenter);

        prompt.readReplayOption();

        assertThat(writer.toString().contains("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void presentsReplayOption() {
        Prompt prompt = new CommandPrompt(defaultReader, writer, plainFormatter, textPresenter);

        prompt.presentReplayOption();

        assertThat(writer.toString().contains("Play again? [Y/N]"), is(true));
    }


    @Test
    public void readsReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer, plainFormatter, textPresenter);

        ReplayOption replayOption = prompt.readReplayOption();

        assertThat(replayOption, is(Y));
    }

    @Test
    public void readsLowerCaseReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("y\n"), writer, plainFormatter, textPresenter);

        ReplayOption replayOption = prompt.readReplayOption();

        assertThat(replayOption, is(Y));
    }


    @Test
    public void clearsScreenWhenReadingReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer, plainFormatter, textPresenter);

        prompt.readReplayOption();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsWhenReplayOptionIsInvalid() {
        Prompt prompt = new CommandPrompt(new StringReader("A\nN\n"), writer, plainFormatter, textPresenter);

        prompt.readReplayOption();

        assertThat(writer.toString().contains(
                CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n[A] is not a valid replay option\n\nPlay again? [Y/N]"), is(true));
    }

    @Test
    public void presentsGameTypes() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        List<GameType> allGameTypes = Arrays.asList(GameType.values());
        prompt.presentGameTypes(allGameTypes);

        assertThat(writer.toString(), is(
                "\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n"
                        + "Enter 1 to play Human vs Human\n"
                        + "Enter 2 to play Human vs Unbeatable\n"
                        + "Enter 3 to play Unbeatable vs Human\n\n"));
    }

    @Test
    public void readsGameType() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }

    @Test
    public void clearsScreenWhenReadingGameTypeOption() {
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsGameTypeWhenAlphaCharacterEntered() {
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, plainFormatter, textPresenter);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));
        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n[a] is not a valid integer\n\nEnter 1 to play Human vs Human"), is(true));
    }

    @Test
    public void repromptsWhenInvalidGameTypeEntered() {
        Prompt prompt = new CommandPrompt(new StringReader("7\n1\n"), writer, plainFormatter, textPresenter);

        GameType gameType = prompt.readGameType(Arrays.asList(GameType.values()));

        assertThat(gameType, is(HUMAN_VS_HUMAN));

        assertThat(writer.toString().contains("[7] is not a valid game type\n\nEnter 1 to play Human vs Human\n"), is(true));
    }

    @Test
    public void printsWinner() {
        CommandPrompt prompt = new CommandPrompt(defaultReader, writer, plainFormatter, textPresenter);
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        prompt.printsWinningMessage(board, X);

        assertThat(writer.toString().contains("XXXOO----"), is(true));
        assertThat(writer.toString().endsWith("\n\nCongratulations - X has won\n"), is(true));
    }

    @Test
    public void printsDrawMessage() {
        CommandPrompt prompt = new CommandPrompt(defaultReader, writer, plainFormatter, textPresenter);
        Board board = new Board(
                X, O, X,
                O, X, X,
                O, X, O
        );

        prompt.printsDrawMessage(board);

        assertThat(writer.toString().contains("XOXOXXOXO"), is(true));
        assertThat(writer.toString().endsWith("No winner this time\n"), is(true));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        new CommandPrompt(defaultReader, writer, plainFormatter, textPresenter);
        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPromptingUser() {
        Prompt commandPrompt = new CommandPrompt(new StringReader("1\n"), writer, plainFormatter, textPresenter);

        commandPrompt.readNextMove(new Board(3));

        assertThat(writer.toString().contains("Please enter the index for your next move\n"), is(true));
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(defaultReader, writerWhichThrowsIOException, plainFormatter, textPresenter);
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        promptWhichThrowsExceptionOnWrite.printsWinningMessage(board, X);
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, writer, plainFormatter, textPresenter);
        promptWhichHasExceptionOnRead.readReplayOption();
    }
}
