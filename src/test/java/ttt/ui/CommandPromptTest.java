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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.GameType.*;
import static ttt.GameType.HUMAN_VS_HUMAN;
import static ttt.ReplayOption.Y;
import static ttt.player.PlayerSymbol.*;


public class CommandPromptTest {
    private static final String CLEAR_SCREEN_ANSI_CHARACTERS = "\033[H\033[2J";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String X_COLOUR = "\033[1;33m";
    private static final String O_COLOUR = "\033[1;31m";

    private static final String PLAY_AGAIN_PROMPT = "Play again? [Y/N]";
    private static final String NEXT_MOVE_PROMPT = "Please enter the index for your next move";
    private static final String A_IS_NOT_A_VALID_NUMBER = "[a] is not a valid integer\n\n";
    private static final String A_IS_NOT_A_NUMBER_REPROMPT = A_IS_NOT_A_VALID_NUMBER + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    public static final String Z_IS_NOT_A_VALID_INTEGER = "[z] is not a valid integer\n\n";
    private static final String Z_IS_NOT_A_NUMBER_MOVE_REPROMPT = Z_IS_NOT_A_VALID_INTEGER + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String DRAW_MESSAGE = "No winner this time";
    private static final String A_IS_NOT_REPLAY_OPTION = "[A] is not a valid replay option\n\n" + FONT_COLOUR_ANSII_CHARACTERS + PLAY_AGAIN_PROMPT;
    private static final String ALREADY_OCCUPIED_CELL_MESSAGE = "[1] is already occupied\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String LARGER_THAN_GRID_BOUNDARY_MESSAGE = "[100] is outside of the grid boundary\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;
    private static final String SMALLER_THAN_GRID_BOUNDARY_MESSAGE = "[-100] is outside of the grid boundary\n\n" + FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT;

    @Test
    public void promptsUserForBoardDimension() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getBoardDimension(HUMAN_VS_HUMAN);

        assertThat(writer.toString().contains("Please enter the dimension of the board you would like to use [1 to 10]\n"), is(true));
    }

    @Test
    public void readsDimensionFromCommandLine() {
        StringReader reader = new StringReader("3\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter());

        int dimension = prompt.getBoardDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is(3));
    }

    @Test
    public void repromptsUserWhenNonNumericEnteredForBoardDimension() {
        StringReader reader = new StringReader("z\n4\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        int dimension = prompt.getBoardDimension(HUMAN_VS_HUMAN);

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                          BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS
                        + Z_IS_NOT_A_VALID_INTEGER
                        + FONT_COLOUR_ANSII_CHARACTERS
                        + "Please enter the dimension of the board you would like to use [1 to 10]\n\n"
                                  + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }


    @Test
    public void repromptsUserWhenInvalidDimensionEnteredForGameType() {
        StringReader reader = new StringReader("100\n4\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        int dimension = prompt.getBoardDimension(HUMAN_VS_UNBEATABLE);

        assertThat(dimension, is(4));
        assertThat(writer.toString().contains(
                BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS
                        + "[100] is outside of the range 1 to 4\n\n"
                        + FONT_COLOUR_ANSII_CHARACTERS
                        + "Please enter the dimension of the board you would like to use [1 to 4]\n\n"
                        + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().contains(vacant3x3Board()), is(true));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer);

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().endsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + vacant3x3Board() + "\n" + A_IS_NOT_A_NUMBER_REPROMPT + "\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board(3));

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter());

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));
    }

    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        StringReader reader = new StringReader("a\nz\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));
        assertThat(writer.toString().contains(A_IS_NOT_A_NUMBER_REPROMPT), is(true));
        assertThat(writer.toString().contains(Z_IS_NOT_A_NUMBER_MOVE_REPROMPT), is(true));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        StringReader reader = new StringReader("100\n-100\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board(3)), equalTo(0));

        assertThat(writer.toString().contains(LARGER_THAN_GRID_BOUNDARY_MESSAGE), is(true));
        assertThat(writer.toString().contains(SMALLER_THAN_GRID_BOUNDARY_MESSAGE), is(true));
    }

    @Test
    public void repromptsWhenMoveIsAlreadyOccupied() {
        StringReader reader = new StringReader("1\n2\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board(X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT)), equalTo(1));
        assertThat(writer.toString().contains(ALREADY_OCCUPIED_CELL_MESSAGE), is(true));
    }

    @Test
    public void clearsScreenBeforePromptingForReplay() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer);

        prompt.getReplayOption();

        assertThat(writer.toString().contains("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void promptsUserForReplayOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("N\n"), writer);

        prompt.getReplayOption();

        assertThat(writer.toString().contains(PLAY_AGAIN_PROMPT), is(true));
    }

    @Test
    public void readsReplayOption() {
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), new StringWriter());

        ReplayOption replayOption = prompt.getReplayOption();

        assertThat(replayOption, is(Y));
    }

    @Test
    public void clearsScreenWhenReadingReplayOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("Y\n"), writer);

        prompt.getReplayOption();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsWhenReplayOptionIsInvalid() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("A\nN\n"), writer);

        prompt.getReplayOption();

        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + A_IS_NOT_REPLAY_OPTION), is(true));
    }

    @Test
    public void promptsForGameType() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getGameType();

        assertThat(writer.toString().contains(FONT_COLOUR_ANSII_CHARACTERS
                + "Enter 1 to play Human vs Human\n"
                + "Enter 2 to play Human vs Unbeatable\n"
                + "Enter 3 to play Unbeatable vs Human\n\n\n"), is(true));
    }

    @Test
    public void readsGameType() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        GameType gameType = prompt.getGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
    }

    @Test
    public void clearsScreenWhenReadingGameTypeOption() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getGameType();

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearsScreenAndRepromptsGameTypeWhenAlphaCharacterEntered() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer);

        GameType gameType = prompt.getGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));
        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n"
                + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS
                + A_IS_NOT_A_VALID_NUMBER
                + FONT_COLOUR_ANSII_CHARACTERS
                + "Enter 1 to play Human vs Human"), is(true));
    }

    @Test
    public void repromptsWhenInvalidGameTypeEntered() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("7\n1\n"), writer);

        GameType gameType = prompt.getGameType();

        assertThat(gameType, is(HUMAN_VS_HUMAN));

        assertThat(writer.toString().contains("[7] is not a valid game type\n\n"
                + FONT_COLOUR_ANSII_CHARACTERS
                + "Enter 1 to play Human vs Human\n"), is(true));
    }


    @Test
    public void printsNew3x3Board() {
        Board board = new Board(3);
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String formattedGrid = vacant3x3Board();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + formattedGrid));
    }

    @Test
    public void printsNew4x4Board() {
        Board board = new Board(4);
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String formattedGrid = vacant4x4Board();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + formattedGrid));
    }

    @Test
    public void prints3x3BoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"));
    }

    @Test
    public void prints4x4BoardWithMoves() {
        Board board = new Board(
                VACANT, VACANT, VACANT, VACANT,
                O, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, X,
                VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String expectedFormattedBoard =
                 "  " + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 10 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 11 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 13 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 14 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 15 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 16 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n"  + expectedFormattedBoard));
    }

    @Test
    public void printsWinner() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printWinningMessageFor(X);

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + "Congratulations - " + X_COLOUR + X.name() + FONT_COLOUR_ANSII_CHARACTERS + " has won\n"));
    }

    @Test
    public void printsDrawMessage() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        CommandPrompt prompt = new CommandPrompt(reader, writer);

        prompt.printDrawMessage();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS + DRAW_MESSAGE + "\n"));
    }

    @Test
    public void clearScreenWhenNewConsoleCreated() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();
        new CommandPrompt(reader, writer);
        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPromptingUser() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader("1\n"), writer);

        commandPrompt.getNextMove(new Board(3));

        assertThat(writer.toString().contains(FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);
        commandPrompt.print(new Board(3));

        assertThat(writer.toString().startsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void colourChangesForSymbolOnBoard() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString().contains(3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test
    public void colourChangesForGrid() {
        Board board = new Board(VACANT, VACANT, VACANT, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString().contains(NUMBER_COLOUR_ANSII_CHARACTERS), is(true));
        assertThat(writer.toString().contains(BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS), is(true));
    }

    @Test(expected = WriteToPromptException.class)
    public void raiseOutputExceptionWhenThereIsAProblemWritingToPrompt() {
        Writer writerWhichThrowsIOException = new WriterStubWhichThrowsExceptionOnWrite();
        Prompt promptWhichThrowsExceptionOnWrite = new CommandPrompt(new StringReader(""), writerWhichThrowsIOException);
        promptWhichThrowsExceptionOnWrite.printWinningMessageFor(X);
    }

    @Test(expected = ReadFromPromptException.class)
    public void raiseInputExceptionWhenThereIsAProblemReadingFromPrompt() {
        Reader readerWhichThrowsIOException = new ReaderStubWhichThrowsExceptionOnRead();
        Prompt promptWhichHasExceptionOnRead = new CommandPrompt(readerWhichThrowsIOException, new StringWriter());

        promptWhichHasExceptionOnRead.getReplayOption();
    }

    private String vacant3x3Board() {
        return "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n "
                + " " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "---------------\n "
                + " " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";
    }

    private String vacant4x4Board() {
        return "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " |  " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n  "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 10 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 11 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 12 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "--------------------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 13 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 14 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 15 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 16 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";
    }
}
