package ttt.board;

import org.junit.Test;
import ttt.player.PlayerSymbol;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ttt.player.PlayerSymbol.*;

public class LineGeneratorTest {

    @Test
    public void identifiesWinningRowOfX() {
        LineGenerator lineGenerator = new LineGenerator(X, X, X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);

        assertTrue(lineGenerator.topRow().isWinning());
    }

    @Test
    public void identifiesWinningRowOfO() {
        LineGenerator lineGenerator = new LineGenerator(X, X, VACANT, O, O, O, VACANT, VACANT, VACANT);

        assertTrue(lineGenerator.middleRow().isWinning());
    }

    @Test
    public void identifiesNoWinningRow() {
        LineGenerator lineGenerator = new LineGenerator(X, X, VACANT, O, O, VACANT, VACANT, VACANT, VACANT);

        assertFalse(lineGenerator.middleRow().isWinning());
    }

    @Test
    public void symbolsOfLine() {
        LineGenerator lineGenerator = new LineGenerator(X, X, X, VACANT, VACANT, VACANT, VACANT, VACANT, VACANT);
        assertTrue(Arrays.equals(lineGenerator.topRow().getSymbols(), new PlayerSymbol[]{X, X, X}));
    }
}