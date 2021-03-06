package ttt.commandlineapp.formatting;

import org.junit.Test;
import ttt.commandlineapp.ValidationResult;
import ttt.game.GameType;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.O;
import static ttt.game.PlayerSymbol.X;

public class ColourTextTest {
    private static final String FONT_COLOUR = "\033[1;37m";
    private static final String X_COLOUR = "\033[1;33m";
    private static final String O_COLOUR = "\033[1;31m";
    private static final String ERROR_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private ColourText colouredText = new ColourText(new StandardTextPresenter());

    @Test
    public void winningMessageForX() {
        String colouredWinningMessage = colouredText.winningMessage(X.getSymbolForDisplay());

        assertThat(colouredWinningMessage, is(FONT_COLOUR + "Congratulations - " + X_COLOUR + "X" + FONT_COLOUR + " has won"));
    }

    @Test
    public void winningMessageForO() {
        String colouredWinningMessage = colouredText.winningMessage(O.getSymbolForDisplay());

        assertThat(colouredWinningMessage, is(FONT_COLOUR + "Congratulations - " + O_COLOUR + "O" + FONT_COLOUR + " has won"));
    }

    @Test
    public void drawMessage() {
        String colouredDrawMessage = colouredText.drawMessage();

        assertThat(colouredDrawMessage, is(FONT_COLOUR + "No winner this time"));
    }

    @Test
    public void validationErrorMessage() {
        String colouredValidationMessage = colouredText.validationError(new ValidationResult("input", false, "invalid reason"));

        assertThat(colouredValidationMessage, is(ERROR_COLOUR_ANSII_CHARACTERS + "invalid reason"));
    }

    @Test
    public void gameTypes() {
        String gameTypes = colouredText.chooseGameTypeMessage(Arrays.asList(GameType.values()));

        assertThat(gameTypes, is(FONT_COLOUR + "Enter 1 to play Human vs Human\n" +
                "Enter 2 to play Human vs Unbeatable\n" +
                "Enter 3 to play Unbeatable vs Human\n"));
    }

    @Test
    public void replayMessage() {
        String replayMessage = colouredText.replayMessage();

        assertThat(replayMessage, is(FONT_COLOUR + "Play again? [Y/N]"));
    }

    @Test
    public void nextMoveMessage() {
        String nextMoveMessage = colouredText.chooseNextMoveMessage();

        assertThat(nextMoveMessage, is(FONT_COLOUR + "Please enter the index for your next move"));
    }

    @Test
    public void chooseDimensionMessage() {
        String dimensionMessage = colouredText.chooseDimensionMessage(1, 4);

        assertThat(dimensionMessage, is(FONT_COLOUR + "Please enter the dimension of the board you would like to use [1 to 4]"));
    }

    @Test
    public void clearMessage() {
        String clearMessage = colouredText.clearMessage();

        assertThat(clearMessage, is("\033[H\033[2J"));
    }
}