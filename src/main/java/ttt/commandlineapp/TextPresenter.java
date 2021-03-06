package ttt.commandlineapp;

import ttt.game.GameType;

import java.util.List;

public interface TextPresenter {
    String winningMessage(String winner);
    String drawMessage();
    String validationError(ValidationResult validationResult);
    String chooseGameTypeMessage(List<GameType> gameTypes);
    String replayMessage();
    String chooseNextMoveMessage();
    String chooseDimensionMessage(int lowerBoundary, int upperBoundary);
    String clearMessage();
}
