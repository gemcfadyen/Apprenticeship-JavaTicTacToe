package ttt.game;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ttt.game.PlayerSymbol.*;

public class PlayerSymbolTest {

    @Test
    public void getOpponentforO() {
        PlayerSymbol opponent = opponent(O);

        assertThat(opponent, is(X));
    }

    @Test
    public void getOpponentforX() {
        PlayerSymbol opponent = opponent(X);

        assertThat(opponent, is(O));
    }
}