package ttt;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;


public class CommandPromptTest {
    private static final String CLEAR_SCREEN_ANSI_CHARACTERS = "\033[H\033[2J";
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private static final String NUMBER_COLOUR_ANSII_CHARACTERS = "\033[1;30m";
    private static final String X_COLOUR = "\033[1;33m";
    private static final String O_COLOUR = "\033[1;31m";

    private static final String A_IS_NOT_A_NUMBER = "[a] is not a valid number. Please re-enter a numeric value";
    private static final String Z_IS_NOT_A_NUMBER = "[z] is not a valid number. Please re-enter a numeric value";
    private static final String PLAY_AGAIN_PROMPT = "Play again? [Y/N]";
    private static final String NEXT_MOVE_PROMPT = "Please enter the index for your next move";
    private static final String DRAW_MESSAGE = "No winner this time";
    private static final String A_IS_NOT_REPLAY_OPTION = "[A] is not a valid replay option. Please re-enter Y/N";
    private static final String ALREADY_OCCUPIED_CELL_MESSAGE = "[1] is already occupied. Please re-enter a valid number within the grid boundary";
    private static final String LARGER_THAN_GRID_BOUNDARY_MESSAGE = "[100] is outside of the grid boundary. Please re-enter a valid number within the grid boundary";
    private static final String SMALLER_THAN_GRID_BOUNDARY_MESSAGE = "[-100] is outside of the grid boundary. Please re-enter a valid number within the grid boundary";

    @Test
    public void displaysBoardWhenPromptingForNextMove() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().contains(vacantBoard()), is(true));
    }

    @Test
    public void repromptClearsScreenBeforePrintingBoard() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("a\n1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().endsWith("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + vacantBoard() + "\n" + A_IS_NOT_A_NUMBER + "\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void clearScreenAfterValidMoveRead() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getNextMove(new Board());

        assertThat(writer.toString().endsWith(CLEAR_SCREEN_ANSI_CHARACTERS + "\n"), is(true));
    }

    @Test
    public void readsNextMoveAsZeroBasedIndex() {
        StringReader reader = new StringReader("1\n");
        Prompt prompt = new CommandPrompt(reader, new StringWriter());

        assertThat(prompt.getNextMove(new Board()), equalTo(0));
    }

    @Test
    public void repromptsWhenAlphaCharacterEnteredAsNextMove() {
        StringReader reader = new StringReader("a\nz\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board()), equalTo(0));
        assertThat(writer.toString().contains(A_IS_NOT_A_NUMBER), is(true));
        assertThat(writer.toString().contains(Z_IS_NOT_A_NUMBER), is(true));
    }

    @Test
    public void repromptsWhenMoveIsOutsideGrid() {
        StringReader reader = new StringReader("100\n-100\n1\n");
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(reader, writer);

        assertThat(prompt.getNextMove(new Board()), equalTo(0));

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

        String replayOption = prompt.getReplayOption();

        assertThat(replayOption, is("Y"));
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

        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + A_IS_NOT_REPLAY_OPTION), is(true));
    }

    @Test
    public void promptsForPlayerSelection() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        prompt.getPlayerOption();

        assertThat(writer.toString().contains(CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + FONT_COLOUR_ANSII_CHARACTERS
                + "Choose opponent:\nEnter 1 to play Human vs Human"), is(true));
    }

    @Test
    public void readsPlayerChoice() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("1\n"), writer);

        int playerChoice = prompt.getPlayerOption();

        assertThat(playerChoice, is(1));
    }

    @Test
    public void repromptsPlayerChoiceWhenAlphaCharacterEntered() {
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader("A\n1\n"), writer);

        int playerChoice = prompt.getPlayerOption();

        assertThat(playerChoice, is(1));
        String s = writer.toString();
        assertThat(s.contains("[A] is not a valid number. Please re-enter a numeric value\n\n"
                + FONT_COLOUR_ANSII_CHARACTERS
                + "Choose opponent:\nEnter 1 to play Human vs Human"), is(true));
    }


    @Test
    public void printsNewBoard() {
        Board board = new Board();
        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        String formattedGrid = vacantBoard();

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + formattedGrid));
    }

    @Test
    public void printsBoardWithMoves() {
        Board board = new Board(VACANT, X, X, O, VACANT, VACANT, VACANT, VACANT, VACANT);

        StringWriter writer = new StringWriter();
        Prompt prompt = new CommandPrompt(new StringReader(""), writer);

        prompt.print(board);

        assertThat(writer.toString(), is("\n" + CLEAR_SCREEN_ANSI_CHARACTERS + "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + X_COLOUR + X.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + O_COLOUR + O.name() + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"));
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

        commandPrompt.getNextMove(new Board());

        assertThat(writer.toString().contains(FONT_COLOUR_ANSII_CHARACTERS + NEXT_MOVE_PROMPT + "\n"), is(true));
    }

    @Test
    public void setsFontColourWhenPrintingGrid() {
        Writer writer = new StringWriter();
        Prompt commandPrompt = new CommandPrompt(new StringReader(""), writer);
        commandPrompt.print(new Board());

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

    private String vacantBoard() {
        return "\n\n" + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + "\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 1 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 2 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 3 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 4 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 5 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 6 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n"
                + "-----------\n "
                + NUMBER_COLOUR_ANSII_CHARACTERS + 7 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 8 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " | " + NUMBER_COLOUR_ANSII_CHARACTERS + 9 + BOARD_OUTLINE_COLOUR_ANSII_CHARACTERS + " \n";
    }
}
