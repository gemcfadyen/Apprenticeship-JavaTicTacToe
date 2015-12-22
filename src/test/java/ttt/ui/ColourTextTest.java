package ttt.ui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class ColourTextTest {
    private static final String FONT_COLOUR = "\033[1;37m";
    public static final String X_COLOUR = "\033[1;33m";
    public static final String O_COLOUR = "\033[1;31m";
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
}