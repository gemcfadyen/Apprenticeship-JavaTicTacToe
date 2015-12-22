package ttt.ui;

public class ColourText implements TextDecorator {
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String X_COLOUR_ANSII_CHARACTERS = "\033[1;33m";

    private TextPresenter textPresenter;

    public ColourText(TextPresenter textPresenter) {
        this.textPresenter = textPresenter;
    }

    @Override
    public String winningMessage(String winner) {
        String decoratedWinningSymbol = colour(winner);
        String colouredWinningMessage = FONT_COLOUR_ANSII_CHARACTERS + textPresenter.winningMessage(decoratedWinningSymbol);
        return colouredWinningMessage;
    }

    private String colour(String symbolForDisplay) {
        //if (symbol.equals(X)) {
        return X_COLOUR_ANSII_CHARACTERS + symbolForDisplay;
        //}
        // return O_COLOUR_ANSII_CHARACTERS + symbol.getSymbolForDisplay();
    }
}
