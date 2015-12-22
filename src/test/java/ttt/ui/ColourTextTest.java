package ttt.ui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.X;

public class ColourTextTest {
    @Test
    public void winningMessageForX() {
        ColourText colouredText = new ColourText(new StandardTextPresenter());
        String colouredWinningMessage = colouredText.winningMessage(X.getSymbolForDisplay());

        assertThat(colouredWinningMessage, is("\033[1;37mCongratulations - \033[1;33mX has won"));
    }

    @Test
    public void winningMessageForO() {

    }
}