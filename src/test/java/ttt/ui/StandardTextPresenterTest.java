package ttt.ui;

import org.junit.Test;
import ttt.GameType;
import ttt.inputvalidation.ValidationResult;

import java.util.Arrays;

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

    @Test
    public void validationMessage() {
        String validationMessage = standardTextPresenter.validationError(new ValidationResult("input", false, "invalid reason"));
        assertThat(validationMessage, is("invalid reason"));
    }

    @Test
    public void gameTypes() {
        String gameTypes = standardTextPresenter.presentGameTypes(Arrays.asList(GameType.values()));
        assertThat(gameTypes, is("Enter 1 to play Human vs Human\n" +
                                 "Enter 2 to play Human vs Unbeatable\n" +
                                 "Enter 3 to play Unbeatable vs Human\n"));
    }
}
