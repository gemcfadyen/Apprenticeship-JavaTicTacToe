package ttt.ui;

import org.junit.Test;
import ttt.GameType;
import ttt.inputvalidation.ValidationResult;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class ColourTextTest {
    private static final String FONT_COLOUR = "\033[1;37m";
    public static final String X_COLOUR = "\033[1;33m";
    public static final String O_COLOUR = "\033[1;31m";
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
        String gameTypes = colouredText.presentGameTypes(Arrays.asList(GameType.values()));
        assertThat(gameTypes, is(FONT_COLOUR + "Enter 1 to play Human vs Human\n" +
                "Enter 2 to play Human vs Unbeatable\n" +
                "Enter 3 to play Unbeatable vs Human\n"));
    }
}