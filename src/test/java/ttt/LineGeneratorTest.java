package ttt;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.arrayContaining;
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
    public void linesForAllDirection() {
        Cell[][] linesForAllDirections = lineGenerator.linesForAllDirections();
        assertThat(linesForAllDirections.length, is(8));
        assertThat(linesForAllDirections[0], arrayContaining(createCell(1, X), createCell(2, O), createCell(3, X)));
        assertThat(linesForAllDirections[1], arrayContaining(createCell(4, VACANT), createCell(5, O), createCell(6, VACANT)));
        assertThat(linesForAllDirections[2], arrayContaining(createCell(7, X), createCell(8, O), createCell(9, X)));
        assertThat(linesForAllDirections[3], arrayContaining(createCell(1, X), createCell(4, VACANT), createCell(7, X)));
        assertThat(linesForAllDirections[4], arrayContaining(createCell(2, O), createCell(5, O), createCell(8, O)));
        assertThat(linesForAllDirections[5], arrayContaining(createCell(3, X), createCell(6, VACANT), createCell(9, X)));
        assertThat(linesForAllDirections[6], arrayContaining(createCell(1, X), createCell(5, O), createCell(9, X)));
        assertThat(linesForAllDirections[7], arrayContaining(createCell(3, X), createCell(5, O), createCell(7, X)));
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