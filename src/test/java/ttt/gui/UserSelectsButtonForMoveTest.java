package ttt.gui;

import org.junit.Test;
import ttt.player.PlayerSymbol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.player.PlayerSymbol.*;
import static ttt.player.PlayerSymbol.X;

public class UserSelectsButtonForMoveTest {

//    @Test
//    public void buttonIsDisabledOnceSelected() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(guiPromptSpy, new GameRulesStub(), button);
//        userSelectsButtonForMove.action();
//
//        assertThat(button.getDisabledStatus(), is(true));
//    }
//
//    @Test
//    public void buttonIsSetToDisabledOnce() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(new GuiPromptSpy(), new GameRulesStub(), button);
//        userSelectsButtonForMove.action();
//        userSelectsButtonForMove.action();
//        assertThat(button.numberOfTimesButtonIsDisabled(), is(1));
//    }
//
//
//    @Test
//    public void userMakesAMove() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GameRulesStub gameRulesStub = new GameRulesStub();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(new GuiPromptSpy(), gameRulesStub, button);
//        userSelectsButtonForMove.action();
//
//        assertThat(gameRulesStub.gameIsStarted(), is(true));
//        assertThat(gameRulesStub.moveTaken(), is(Integer.valueOf(button.getId())));
//    }
//
//
//    @Test
//    public void boardUpdatedToReflectPlayersMove() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(new GuiPromptSpy(), new GameRulesStub(), button);
//        userSelectsButtonForMove.action();
//
//        assertThat(button.getLabel(), is(X.getSymbolForDisplay()));
//    }
//
//    @Test
//    public void promptDisplaysWinningMessage() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GameRulesStub gameRulesStub = new GameRulesStub(true, false);
//        GuiPromptSpy promptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(promptSpy, gameRulesStub, button);
//        userSelectsButtonForMove.action();
//
//        assertThat(promptSpy.numberOfTimesWinPrinted(), is(1));
//    }
//
//    @Test
//    public void promptDisplaysDrawMessage() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GameInterface gameRulesStub = new GameRulesStub(false, false);
//        GuiPromptSpy promptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(promptSpy, gameRulesStub, button);
//        userSelectsButtonForMove.action();
//
//        assertThat(promptSpy.numberOfTimesDrawPrinted(), is(1));
//        assertThat(promptSpy.numberOfTimesWinPrinted(), is(0));
//    }
//
//    @Test
//    public void playerIsToggled() {
//        DeactivableElementSpy button = new DeactivableElementSpy();
//        GameRulesSpy gameRulesSpy = new GameRulesSpy();
//        GuiPromptSpy promptSpy = new GuiPromptSpy();
//        UserSelectsButtonForMove userSelectsButtonForMove = new UserSelectsButtonForMove(promptSpy, gameRulesSpy, button);
//
//        userSelectsButtonForMove.action();
//
//        assertThat(gameRulesSpy.hasToggledPlayers(), is(true));
//    }
//
//    private static class DeactivableElementSpy implements DeactivatableElement {
//        private boolean isDisabled = false;
//        private int numberOfTimesButtonIsDisabled = 0;
//        private String text;
//
//        @Override
//        public void setClickAction(ClickEvent clickEvent) {
//
//        }
//
//        @Override
//        public void setText(String text) {
//            this.text = text;
//        }
//
//        @Override
//        public String getText() {
//            return text;
//        }
//
//
//        @Override
//        public void setDisabled() {
//            numberOfTimesButtonIsDisabled++;
//            isDisabled = true;
//        }
//
//        @Override
//        public String getId() {
//            return "7";
//        }
//
//        public boolean getDisabledStatus() {
//            return isDisabled;
//        }
//
//        public int numberOfTimesButtonIsDisabled() {
//            return numberOfTimesButtonIsDisabled;
//        }
//
//        public String getLabel() {
//            return text;
//        }
//    }

//    private static class GameRulesStub implements GameInterface {
//        private boolean hasFreeSpace = true;
//        private boolean gameIsStarted = false;
//        private int move;
//        private boolean hasWinner = false;
//
//        public GameRulesStub() {
//        }
//
//        public GameRulesStub(boolean hasWinner, boolean hasFreeSpace) {
//            this.hasWinner = hasWinner;
//            this.hasFreeSpace = hasFreeSpace;
//        }
//
//        public void playMoveAt(String move) {
//            gameIsStarted = true;
//            this.move = Integer.valueOf(move);
//        }
//
//        public PlayerSymbol getCurrentPlayer() {
//            return X;
//        }
//
//        @Override
//        public void togglePlayer() {
//        }
//
//        @Override
//        public void initialiseGame(int dimension) {
//
//        }
//
//        public boolean gameIsStarted() {
//            return gameIsStarted;
//        }
//
//        public boolean boardHasFreeSpace() {
//            return hasFreeSpace;
//        }
//
//        public boolean hasWinner() {
//            return hasWinner;
//        }
//
//        public int moveTaken() {
//            return move;
//        }
//
//    }
//
//    private static class GameRulesSpy implements GameInterface {
//        private boolean hasToggledPlayers = false;
//        private int currentPlayer = 0;
//
//        @Override
//        public void playMoveAt(String move) {
//
//        }
//
//        @Override
//        public PlayerSymbol getCurrentPlayer() {
//            return currentPlayer == 1 ? O : X;
//        }
//
//        @Override
//        public boolean hasWinner() {
//            return false;
//        }
//
//        @Override
//        public boolean boardHasFreeSpace() {
//            return false;
//        }
//
//        public void togglePlayer() {
//            hasToggledPlayers = true;
//            currentPlayer = (currentPlayer == 1) ? 0 : 1;
//        }
//
//        @Override
//        public void initialiseGame(int dimension) {
//
//        }
//
//        public boolean hasToggledPlayers() {
//            return hasToggledPlayers;
//        }
//    }
}
