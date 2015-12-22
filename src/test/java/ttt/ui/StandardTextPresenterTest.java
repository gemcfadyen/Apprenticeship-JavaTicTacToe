package ttt.ui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.X;

public class StandardTextPresenterTest {

    private StandardTextPresenter standardTextPresenter = new StandardTextPresenter();

    @Test
    public void printsWinningMessage() {
        String winningMessage = standardTextPresenter.winningMessage(X.getSymbolForDisplay());
        assertThat(winningMessage, is("Congratulations - X has won"));
    }

    @Test
    public void printsDrawMessage() {
        String drawMessage = standardTextPresenter.drawMessage();
        assertThat(drawMessage, is("No winner this time"));
    }
}
