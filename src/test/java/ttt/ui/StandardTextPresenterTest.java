package ttt.ui;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.X;

public class StandardTextPresenterTest {
    @Test
    public void printsWinningMessage() {
        StandardTextPresenter presenter = new StandardTextPresenter();
        String winningMessage = presenter.winningMessage(X.getSymbolForDisplay());
        assertThat(winningMessage, is("Congratulations - X has won"));
    }
}
