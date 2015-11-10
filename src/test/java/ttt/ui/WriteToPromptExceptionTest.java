package ttt.ui;

import org.junit.Before;
import org.junit.Test;
import ttt.ui.WriteToPromptException;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class WriteToPromptExceptionTest {

    private IOException originalException;
    private WriteToPromptException wrappedException;

    @Before
    public void setup() {
        originalException = new IOException();
        wrappedException = new WriteToPromptException("useful message", originalException);
    }

    @Test
    public void preservesMessage() {
        assertThat(wrappedException.getMessage(), equalTo("useful message"));
    }

    @Test
    public void preservesThrowable() {
        assertThat(wrappedException.getCause(), equalTo(originalException));
    }
}