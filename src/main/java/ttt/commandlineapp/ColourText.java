package ttt.commandlineapp;

import ttt.game.GameType;

import java.util.List;

import static ttt.game.PlayerSymbol.X;

public class ColourText implements TextDecorator {
    private static final String FONT_COLOUR_ANSII_CHARACTERS = "\033[1;37m";
    private static final String X_COLOUR_ANSII_CHARACTERS = "\033[1;33m";
    private static final String O_COLOUR_ANSII_CHARACTERS = "\033[1;31m";
    private static final String ERROR_COLOUR_ANSII_CHARACTERS = "\033[1;36m";
    private TextPresenter textPresenter;

    public ColourText(TextPresenter textPresenter) {
        this.textPresenter = textPresenter;
    }

    @Override
    public String winningMessage(String winner) {
        String decoratedWinningSymbol = colour(winner) + FONT_COLOUR_ANSII_CHARACTERS;
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.winningMessage(decoratedWinningSymbol);
    }

    @Override
    public String drawMessage() {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.drawMessage();
    }

    @Override
    public String validationError(ValidationResult validationResult) {
        return ERROR_COLOUR_ANSII_CHARACTERS + textPresenter.validationError(validationResult);
    }

    @Override
    public String chooseGameTypeMessage(List<GameType> gameTypes) {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.chooseGameTypeMessage(gameTypes);
    }

    @Override
    public String replayMessage() {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.replayMessage();
    }

    @Override
    public String chooseNextMoveMessage() {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.chooseNextMoveMessage();
    }

    @Override
    public String chooseDimensionMessage(int lowerBoundary, int upperBoundary) {
        return FONT_COLOUR_ANSII_CHARACTERS + textPresenter.chooseDimensionMessage(lowerBoundary, upperBoundary);
    }

    @Override
    public String clearMessage() {
        return textPresenter.clearMessage();
    }

    private String colour(String symbolForDisplay) {
        if (symbolForDisplay.equals(X.getSymbolForDisplay())) {
            return X_COLOUR_ANSII_CHARACTERS + symbolForDisplay;
        }
        return O_COLOUR_ANSII_CHARACTERS + symbolForDisplay;
    }
}
