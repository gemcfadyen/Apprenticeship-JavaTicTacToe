package ttt.ui;

import static ttt.player.PlayerSymbol.X;

public class ColourText implements TextDecorator {
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String X_COLOUR_ANSII_CHARACTERS = "\033[1;33m";
    private static final String O_COLOUR_ANSII_CHARACTERS = "\033[1;31m";

    private TextPresenter textPresenter;

    public ColourText(TextPresenter textPresenter) {
        this.textPresenter = textPresenter;
    }

    @Override
    public String winningMessage(String winner) {
        String decoratedWinningSymbol = colour(winner);
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.winningMessage(decoratedWinningSymbol);
    }

    @Override
    public String drawMessage() {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.drawMessage();
    }

    private String colour(String symbolForDisplay) {
        if (symbolForDisplay.equals(X.getSymbolForDisplay())) {
            return X_COLOUR_ANSII_CHARACTERS + symbolForDisplay;
        }
        return O_COLOUR_ANSII_CHARACTERS + symbolForDisplay;
    }
}
