package ttt;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.PlayerSymbol.*;

public class LineGeneratorTest {
    private Cell[] grid;
    private LineGenerator lineGenerator;

    @Before
    public void setup() {
        grid = initialGridSetup();
        lineGenerator = new LineGenerator(grid);
    }

    @Test
    public void topRow() {
        assertThat(lineGenerator.topRow(), is(new Cell[]{createCell(1, X), createCell(2, O), createCell(3, X)}));
    }

    @Test
    public void middleRow() {
        assertThat(lineGenerator.middleRow(), is(new Cell[]{createCell(4, VACANT), createCell(5, O), createCell(6, VACANT)}));
    }


    @Test
    public void bottomRow() {
        assertThat(lineGenerator.bottomRow(), is(new Cell[]{createCell(7, X), createCell(8, O), createCell(9, X)}));
    }

    @Test
    public void leftColumn() {
        assertThat(lineGenerator.leftColumn(), is(new Cell[]{createCell(1, X), createCell(4, VACANT), createCell(7, X)}));
    }


    @Test
    public void middleColumn() {
        assertThat(lineGenerator.middleColumn(), is(new Cell[]{createCell(2, O), createCell(5, O), createCell(8, O)}));
    }

    @Test
    public void rightColumn() {
        assertThat(lineGenerator.rightColumn(), is(new Cell[]{createCell(3, X), createCell(6, VACANT), createCell(9, X)}));
    }

    @Test
    public void backslashDiagonal() {
        assertThat(lineGenerator.backslashDiagonal(), is(new Cell[]{createCell(1, X), createCell(5, O), createCell(9, X)}));
    }

    @Test
    public void forwardslashDiagonal() {
        assertThat(lineGenerator.forwardslashDiagonal(), is(new Cell[]{createCell(3, X), createCell(5, O), createCell(7, X)}));
    }

    private Cell[] initialGridSetup() {
        return new Cell[] {
                    createCell(1, X), createCell(2, O), createCell(3, X),
                    createCell(4, VACANT), createCell(5, O), createCell(6, VACANT),
                    createCell(7, X), createCell(8, O), createCell(9, X)};
    }

    private Cell createCell(int offset, PlayerSymbol x) {
        return new Cell(offset, x);
    }

}