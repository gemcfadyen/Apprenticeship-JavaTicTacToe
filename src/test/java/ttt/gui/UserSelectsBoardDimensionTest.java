package ttt.gui;

import org.junit.Test;
import ttt.board.BoardFactory;
import ttt.player.PlayerFactory;
import ttt.player.PlayerSymbol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserSelectsBoardDimensionTest {

//    @Test
//    public void presentsBoardWhenDimensionIsSelected() {
//        GuiPromptSpy guiPromptSpy = new GuiPromptSpy();
//        ClickableElement dimensionSelectionButton = new ClickableElementStub();
//        GameRulesSpy gameRuleSpy = new GameRulesSpy();
//        UserSelectsBoardDimension userSelectsBoardDimension = new UserSelectsBoardDimension(gameRuleSpy, guiPromptSpy, dimensionSelectionButton);
//        userSelectsBoardDimension.action();
//
//        assertThat(guiPromptSpy.getNumberOfTimesBoardIsPrinted(), is(1));
//        assertThat(gameRuleSpy.boardCreated(), is(true));
//    }
//
//    private class ClickableElementStub implements ClickableElement {
//        @Override
//        public void setClickAction(ClickEvent clickEvent) {
//
//        }
//
//        @Override
//        public String getText() {
//            return "3x3";
//        }
//    }
//
//    private static class GameRulesSpy implements GameInterface {
//        private boolean boardCreated = false;
//
//        @Override
//        public void playMoveAt(String move) {
//
//        }
//
//        @Override
//        public PlayerSymbol getCurrentPlayer() {
//            return null;
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
//        @Override
//        public void togglePlayer() {
//
//        }
//
//        @Override
//        public void createBoard(int dimension) {
//            boardCreated = true;
//        }
//
//        public boolean boardCreated() {
//            return boardCreated;
//        }
//
//    }
}
