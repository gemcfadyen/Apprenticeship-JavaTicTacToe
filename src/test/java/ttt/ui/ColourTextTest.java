package ttt.ui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.O;
import static ttt.player.PlayerSymbol.X;

public class ColourTextTest {

    private ColourText colouredText = new ColourText(new StandardTextPresenter());

    @Test
    public void winningMessageForX() {
        String colouredWinningMessage = colouredText.winningMessage(X.getSymbolForDisplay());

        assertThat(colouredWinningMessage, is("\033[1;37mCongratulations - \033[1;33mX has won"));
    }

    @Test
    public void winningMessageForO() {
        String colouredWinningMessage = colouredText.winningMessage(O.getSymbolForDisplay());

        assertThat(colouredWinningMessage, is("\033[1;37mCongratulations - \033[1;31mO has won"));
    }

    @Test
    public void drawMessage() {
        String colouredDrawMessage = colouredText.drawMessage();
        assertThat(colouredDrawMessage, is("\033[1;37mNo winner this time"));
    }

}