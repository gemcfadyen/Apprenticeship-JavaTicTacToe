package ttt.gui;

import org.junit.Rule;
import org.junit.Test;

public class ApplicationTest {

    @Rule public JavaFXThreadingRule javaFXThreadingRule;

    @Test
    public void applicationStarts() {
        JavaFxGui gui = new JavaFxGui();
    }
}
