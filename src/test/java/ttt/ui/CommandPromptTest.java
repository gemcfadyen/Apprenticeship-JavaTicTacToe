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
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";

    private static final String PLAY_AGAIN_PROMPT = "Play again? [Y/N]";
    private static final String NEXT_MOVE_PROMPT = "Please enter the index for your next move";
    private static final String A_IS_NOT_A_VALID_NUMBER = "[a] is not a valid integer\n\n";
    private static final String A_IS_NOT_A_NUMBER_REPROMPT = A_IS_NOT_A_VALID_NUMBER + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String Z_IS_NOT_A_VALID_INTEGER = "[z] is not a valid integer\n\n";
    private static final String Z_IS_NOT_A_NUMBER_MOVE_REPROMPT = Z_IS_NOT_A_VALID_INTEGER + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String DRAW_MESSAGE = "No winner this time";
    private static final String A_IS_NOT_REPLAY_OPTION = "[A] is not a valid replay option\n\n" + PLAY_AGAIN_PROMPT;
    private static final String ALREADY_OCCUPIED_CELL_MESSAGE = "[1] is already occupied\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String LARGER_THAN_GRID_BOUNDARY_MESSAGE = "[100] is outside of the grid boundary\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String SMALLER_THAN_GRID_BOUNDARY_MESSAGE = "[-100] is outside of the grid boundary\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;

    @Test
    public void askUserForBoardDimension() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        prompt.presentGridDimensionsUpTo("5");

        assertThat(writer.toString().contains("Please enter the dimension of the board you would like to use [1 to 5]\n"), is(true));
    }

    @Test
    public void readsDimensionFromCommandLine() {
        StringReader reader = new StringReader("3\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter(), new PlainFormatter());

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(3));
    }

    @Test
    public void repromptsUserWhenNonNumericEnteredForBoardDimension() {
        StringReader reader = new StringReader("z\n4\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());

        int dimension = prompt.readBoardDimension(HUMAN_VS_HUMAN.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                          Z_IS_NOT_A_VALID_INTEGER
                        + "Please enter the dimension of the board you would like to use [1 to 5]\n\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }


    @Test
    public void repromptsUserWhenInvalidDimensionEnteredForGameType() {
        StringReader reader = new StringReader("100\n4\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());

        int dimension = prompt.readBoardDimension(HUMAN_VS_UNBEATABLE.dimensionUpperBoundary());

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                          "[100] is outside of the range 1 to 4\n\n"
                        + "Please enter the dimension of the board you would like to use [1 to 4]\n\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().contains("---------"), is(true));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, new PlainFormatter());

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().endsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + "---------" + "\n\n" + A_IS_NOT_A_NUMBER_REPROMPT + "\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter(), new PlainFormatter());

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));
    }

    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        StringReader reader = new StringReader("a\nz\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));
        assertThat(writer.toString().contains(A_IS_NOT_A_NUMBER_REPROMPT), is(true));
        assertThat(writer.toString().contains(Z_IS_NOT_A_NUMBER_MOVE_REPROMPT), is(true));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        StringReader reader = new StringReader("100\n-100\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));

        assertThat(writer.toString().contains(LARGER_THAN_GRID_BOUNDARY_MESSAGE), is(true));
        assertThat(writer.toString().contains(SMALLER_THAN_GRID_BOUNDARY_MESSAGE), is(true));
    }

    @Test
    public void repromptsWhenMoveIsAlreadyOccupied() {
        StringReader reader = new StringReader("1\n2\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());

        assertThat(prompt.getNextMove(new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT)), equalTo(1));
        assertThat(writer.toString().contains(ALREADY_OCCUPIED_CELL_MESSAGE), is(true));
    }

    @Test
    public void clearsScreenBeforePromptingForReplay() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer, new PlainFormatter());

        prompt.getReplayOption();

        assertThat(writer.toString().contains("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void promptsUserForReplayOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer, new PlainFormatter());

        prompt.getReplayOption();

        assertThat(writer.toString().contains(PLAY_AGAIN_PROMPT), is(true));
    }

    @Test
    public void readsReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), new StringWriter(), new PlainFormatter());

        ReplayOption replayOption = prompt.getReplayOption();

        assertThat(replayOption, is(Y));
    }

    @Test
    public void clearsScreenWhenReadingReplayOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer, new PlainFormatter());

        prompt.getReplayOption();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsWhenReplayOptionIsInvalid() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("A\nN\n"), writer, new PlainFormatter());

        prompt.getReplayOption();

        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + A_IS_NOT_REPLAY_OPTION), is(true));
    }

    @Test
    public void presentsGameTypes() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        List<GameType> allGameTypes = Arrays.asList(GameType.values());
        prompt.presentGameTypes(allGameTypes);

        assertThat(writer.toString(), is(
                "\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n"
                        + FONT_COLOUR_ANSII_CHARACTERS
                        + "Enter 1 to play Human vs Human\n"
                        + "Enter 2 to play Human vs Unbeatable\n"
                        + "Enter 3 to play Unbeatable vs Human\n\n"));
    }

    @Test
    public void readsGameType() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        GameType gameType = prompt.readGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }

    @Test
    public void clearsScreenWhenReadingGameTypeOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        prompt.readGameType();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsGameTypeWhenAlphaCharacterEntered() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer, new PlainFormatter());

        GameType gameType = prompt.readGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n"
                + A_IS_NOT_A_VALID_NUMBER
                + FONT_COLOUR_ANSII_CHARACTERS
                + "Enter 1 to play Human vs Human"), is(true));
    }

    @Test
    public void repromptsWhenInvalidGameTypeEntered() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("7\n1\n"), writer, new PlainFormatter());

        GameType gameType = prompt.readGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));

        assertThat(writer.toString().contains("[7] is not a valid game type\n\n"
                + FONT_COLOUR_ANSII_CHARACTERS
                + "Enter 1 to play Human vs Human\n"), is(true));
    }

    @Test
    public void printsWinner() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        prompt.printsWinningMessage(board, X);

        assertThat(writer.toString().contains("XXXOO----"), is(true));
        assertThat(writer.toString().endsWith("\n\n" + "Congratulations - X has won" + "\n"), is(true));
    }

    @Test
    public void printsDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer, new PlainFormatter());
        Board board = new Board(
                X, O, X,
                O, X, X,
                O, X, O
        );

        prompt.printsDrawMessage(board);

        assertThat(writer.toString().contains("XOXOXXOXO"), is(true));
        assertThat(writer.toString().endsWith(DRAW_MESSAGE + "\n"), is(true));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        new CommandPrompt(reader, writer, new PlainFormatter());
        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPromptingUser() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader("1\n"), writer, new PlainFormatter());

        commandPrompt.getNextMove(new Board(3));

        assertThat(writer.toString().contains(FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT + "\n"), is(true));
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(new StringReader(""), writerWhichThrowsIOException, new PlainFormatter());
        Board board = new Board(
                X, X, X,
                O, O, VACANT,
                VACANT, VACANT, VACANT);

        promptWhichThrowsExceptionOnWrite.printsWinningMessage(board, X);
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, new StringWriter(), new PlainFormatter());

        promptWhichHasExceptionOnRead.getReplayOption();
    }
}
