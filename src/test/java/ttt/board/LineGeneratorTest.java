package ttt.board;

import org.junit.Assert;
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
    public void allLinesIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                O, VACANT, VACANT
        );
        Line[] lines = lineGenerator.linesForAllDirections();
        assertThat(lines.length, is(8));

        Assert.assertThat(lines[0].getSymbols(), is(new PlayerSymbol[]{X, X, X}));
        Assert.assertThat(lines[1].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT}));
        Assert.assertThat(lines[2].getSymbols(), is(new PlayerSymbol[]{O, VACANT, VACANT}));
        Assert.assertThat(lines[3].getSymbols(), is(new PlayerSymbol[]{X, VACANT, O}));
        Assert.assertThat(lines[4].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        Assert.assertThat(lines[5].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        Assert.assertThat(lines[6].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        Assert.assertThat(lines[7].getSymbols(), is(new PlayerSymbol[]{X, VACANT, O}));
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
     public void symbolsOfLineIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, X,
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, O, VACANT
        );
        Line[] horizontalRows = lineGenerator.getRows();
        PlayerSymbol[] symbols = horizontalRows[0].getSymbols();
        assertTrue(Arrays.equals(symbols, new PlayerSymbol[]{X, X, X, X}));
    }

    @Test
    public void getsFourHorizontalRowsFor4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, O, VACANT, VACANT
        );
        Line[] rows = lineGenerator.getRows();
        assertThat(rows.length, is(4));
        Assert.assertThat(rows[0].getSymbols(), is(new PlayerSymbol[]{X, X, X, VACANT}));
        Assert.assertThat(rows[1].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        Assert.assertThat(rows[2].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        Assert.assertThat(rows[3].getSymbols(), is(new PlayerSymbol[]{VACANT, O, VACANT, VACANT}));
    }

    @Test
    public void allLinesIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, O,
                VACANT, O, VACANT, VACANT
        );
        Line[] lines = lineGenerator.linesForAllDirections();
        assertThat(lines.length, is(10));

        Assert.assertThat(lines[0].getSymbols(), is(new PlayerSymbol[]{X, X, X, VACANT}));
        Assert.assertThat(lines[1].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        Assert.assertThat(lines[2].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, O}));
        Assert.assertThat(lines[3].getSymbols(), is(new PlayerSymbol[]{VACANT, O, VACANT, VACANT}));
        Assert.assertThat(lines[4].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        Assert.assertThat(lines[5].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, O}));
        Assert.assertThat(lines[6].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        Assert.assertThat(lines[7].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, O, VACANT}));
        Assert.assertThat(lines[8].getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        Assert.assertThat(lines[9].getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
    }
}