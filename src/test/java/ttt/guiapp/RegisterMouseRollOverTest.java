package ttt.guiapp;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RegisterMouseRollOverTest {
    private RollableElementSpy anyRollableElement = new RollableElementSpy();

    @Test
    public void eventRegisteredOnRollOff() {
        new RegisterRollEvent().register(anyRollableElement, new RollOffStub());

        assertThat(anyRollableElement.hasMouseRolledOffEvent(), is(true));
    }

    @Test
    public void eventRegisteredOnRollOver() {
        new RegisterRollEvent().register(anyRollableElement, new RollOnStub());

        assertThat(anyRollableElement.hasMouseRolledOnEvent(), is(true));
    }
}
