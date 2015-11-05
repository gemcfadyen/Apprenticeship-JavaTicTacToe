package ttt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class CellTest {


    @Test
    public void returnsOffsetOfCell() {
        Cell cell = new Cell(3, X);
        assertThat(cell.getOffset(), is(3));
    }

    @Test
    public void returnsPlayerSymbolInCell() {
        Cell cell = new Cell(2, O);
        assertThat(cell.getSymbol(), is(O));
    }

    @Test
    public void setsSymbol() {
        Cell cell = new Cell(1, VACANT);
        cell.setSymbol(X);

        assertThat(cell.getSymbol(), is(X));
    }

    @Test
    public void comparingCellToAnotherClassWillNotBeEqual() {
        Cell one = new Cell(1, X);
        Object two = new Object();

        assertThat(one.equals(two), is(false));
    }

    @Test
    public void cellsWithDifferentOffsetAreNotEqual() {
        Cell one = new Cell(8, X);
        Cell two = new Cell(2, X);

        assertThat(one.equals(two), is(false));
    }

    @Test
    public void cellsWithDifferentSymbolsAreNotEqual() {
        Cell one = new Cell(2, VACANT);
        Cell two = new Cell(2, X);

        assertThat(one.equals(two), is(false));
    }

    @Test
    public void cellsWithTheSameTypeAndSymbolAndOffsetAreEqual() {
        Cell one = new Cell(2, X);
        Cell two = new Cell(2, X);

        assertThat(one.equals(two), is(true));
    }

    @Test
    public void equalCellsHaveTheSameHashCode() {
        Cell one = new Cell(2, X);
        Cell two = new Cell(2, X);

        assertThat(one.hashCode(), is(two.hashCode()));
    }

    @Test
    public void cellsThatHaveDifferentOffsetsHaveDifferentPrimes() {
        Cell one = new Cell(2, X);
        Cell two = new Cell(8, X);

        assertThat(one.hashCode(), is(not(two.hashCode())));
    }
}
