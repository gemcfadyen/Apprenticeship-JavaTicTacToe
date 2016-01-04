package ttt.game.board;

import org.junit.Test;
import ttt.game.PlayerSymbol;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ttt.game.PlayerSymbol.*;

public class LineGeneratorTest {

    @Test
    public void identifiesWinningRowOfXIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT
        );

        List<Line> horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows.get(0).isWinning());
    }

    @Test
    public void identifiesWinningRowOfOIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, VACANT,
                O, O, O,
                VACANT, VACANT, VACANT
        );

        List<Line> horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows.get(1).isWinning());
    }

    @Test
    public void identifiesNoWinningRowIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, VACANT,
                O, O, VACANT,
                VACANT, VACANT, VACANT
        );

        List<Line> horizontalRows = lineGenerator.getRows();
        assertFalse(horizontalRows.get(2).isWinning());
    }

    @Test
    public void symbolsOfLineIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT
        );
        List<Line> horizontalRows = lineGenerator.getRows();
        assertTrue(Arrays.equals(horizontalRows.get(0).getSymbols(), new PlayerSymbol[]{X, X, X}));
    }

    @Test
    public void allLinesIn3x3() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X,
                VACANT, VACANT, VACANT,
                O, VACANT, VACANT
        );
        List<Line> lines = lineGenerator.linesForAllDirections();
        assertThat(lines.size(), is(8));

        assertThat(lines.get(0).getSymbols(), is(new PlayerSymbol[]{X, X, X}));
        assertThat(lines.get(1).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT}));
        assertThat(lines.get(2).getSymbols(), is(new PlayerSymbol[]{O, VACANT, VACANT}));
        assertThat(lines.get(3).getSymbols(), is(new PlayerSymbol[]{X, VACANT, O}));
        assertThat(lines.get(4).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        assertThat(lines.get(5).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        assertThat(lines.get(6).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT}));
        assertThat(lines.get(7).getSymbols(), is(new PlayerSymbol[]{X, VACANT, O}));
    }

    @Test
    public void identifiesNoWinIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                O, O, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );

        List<Line> horizontalRows = lineGenerator.getRows();
        assertFalse(horizontalRows.get(0).isWinning());
    }

    @Test
    public void identifiesWinningRowOfXIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, X,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, VACANT
        );

        List<Line> horizontalRows = lineGenerator.getRows();
        assertTrue(horizontalRows.get(0).isWinning());
    }

    @Test
    public void identifiesWinningColumnIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT,
                X, VACANT, VACANT, VACANT
        );

        List<Line> rows = lineGenerator.linesForAllDirections();
        assertTrue(rows.get(4).isWinning());
    }

    @Test
    public void identifiesWinningBackslashDiagonalIn4x4Grid() {
        LineGenerator lineGenerator = new LineGenerator(
                X, VACANT, VACANT, VACANT,
                VACANT, X, VACANT, VACANT,
                VACANT, VACANT, X, VACANT,
                VACANT, VACANT, VACANT, X
        );

        List<Line> rows = lineGenerator.linesForAllDirections();
        assertTrue(rows.get(8).isWinning());
    }

    @Test
    public void identifiesWinningForwardslashDiagonalIn4x4Grid() {
        LineGenerator lineGenerator = new LineGenerator(
                VACANT, VACANT, VACANT, X,
                VACANT, VACANT, X, VACANT,
                VACANT, X, VACANT, VACANT,
                X, VACANT, VACANT, VACANT
        );

        List<Line> rows = lineGenerator.linesForAllDirections();
        assertTrue(rows.get(9).isWinning());
    }

    @Test
     public void symbolsOfLineIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, X,
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, VACANT, O,
                VACANT, VACANT, O, VACANT
        );
        List<Line> horizontalRows = lineGenerator.getRows();
        PlayerSymbol[] symbols = horizontalRows.get(0).getSymbols();
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
        List<Line> rows = lineGenerator.getRows();
        assertThat(rows.size(), is(4));
        assertThat(rows.get(0).getSymbols(), is(new PlayerSymbol[]{X, X, X, VACANT}));
        assertThat(rows.get(1).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        assertThat(rows.get(2).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        assertThat(rows.get(3).getSymbols(), is(new PlayerSymbol[]{VACANT, O, VACANT, VACANT}));
    }

    @Test
    public void allLinesIn4x4() {
        LineGenerator lineGenerator = new LineGenerator(
                X, X, X, VACANT,
                VACANT, VACANT, VACANT, VACANT,
                VACANT, VACANT, VACANT, O,
                VACANT, O, VACANT, VACANT
        );
        List<Line> lines = lineGenerator.linesForAllDirections();
        assertThat(lines.size(), is(10));

        assertThat(lines.get(0).getSymbols(), is(new PlayerSymbol[]{X, X, X, VACANT}));
        assertThat(lines.get(1).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
        assertThat(lines.get(2).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, O}));
        assertThat(lines.get(3).getSymbols(), is(new PlayerSymbol[]{VACANT, O, VACANT, VACANT}));
        assertThat(lines.get(4).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        assertThat(lines.get(5).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, O}));
        assertThat(lines.get(6).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        assertThat(lines.get(7).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, O, VACANT}));
        assertThat(lines.get(8).getSymbols(), is(new PlayerSymbol[]{X, VACANT, VACANT, VACANT}));
        assertThat(lines.get(9).getSymbols(), is(new PlayerSymbol[]{VACANT, VACANT, VACANT, VACANT}));
    }
}