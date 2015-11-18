package ttt.board;

import org.junit.Test;
import ttt.player.PlayerSymbol;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ttt.player.PlayerSymbol.*;

public class LineGeneratorTest {

    @Test
    public void identifiesWinningRowOfXIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT
        );

        Line[] horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows[0].isWinning());
    }

    @Test
    public void identifiesWinningRowOfOIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, VACANT,
                O, O, O,
                VACANT, VACANT, VACANT
        );

        Line[] horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows[1].isWinning());
    }

    @Test
    public void identifiesNoWinningRowIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, VACANT,
                O, O, VACANT,
                VACANT, VACANT, VACANT
        );

        Line[] horizontalRows = lineGenerator.getRows();
        assertFalse(horizontalRows[2].isWinning());
    }

    @Test
    public void symbolsOfLineIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT
        );
        Line[] horizontalRows = lineGenerator.getRows();
        assertTrue(Arrays.equals(horizontalRows[0].getSymbols(), new PlayerSymbol[]{X, X, X}));
    }

    @Test
    public void getsFourHorizontalRowsFor4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(lineGenerator.getRows().length, is(4));
    }

    @Test
    public void identifiesNoWinIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                O, O, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );

        Line[] horizontalRows = lineGenerator.getRows();
        assertFalse(horizontalRows[0].isWinning());
    }

    @Test
    public void identifiesWinningRowOfXIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, X,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );

        Line[] horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows[0].isWinning());
    }

    @Test
    public void identifiesWinningColumnIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT
        );

        Line[] rows = lineGenerator.linesForAllDirections();
        assertTrue(rows[4].isWinning());
    }

    @Test
    public void identifiesWinningBackslashDiagonalIn4x4Grid() {
        LineGenerator lineGenerator = new LineGenerator(
                X, VACANT, VACANT, VACANT,
                VACANT, X, VACANT, VACANT,
                VACANT, VACANT, X, VACANT,
                VACANT, VACANT, VACANT, X
        );

        Line[] rows = lineGenerator.linesForAllDirections();
        assertTrue(rows[8].isWinning());
    }

    @Test
    public void identifiesWinningForwardslashDiagonalIn4x4Grid() {
        LineGenerator lineGenerator = new LineGenerator(
                VACANT, VACANT, VACANT, X,
                VACANT, VACANT, X, VACANT,
                VACANT, X, VACANT, VACANT,
                X, VACANT, VACANT, VACANT
        );

        Line[] rows = lineGenerator.linesForAllDirections();
        assertTrue(rows[9].isWinning());
    }


    @Test
    public void allLinesIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );
        assertThat(lineGenerator.linesForAllDirections().length, is(10));
    }

}