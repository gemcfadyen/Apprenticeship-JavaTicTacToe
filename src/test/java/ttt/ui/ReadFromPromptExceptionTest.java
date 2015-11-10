package ttt.ui;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ReadFromPromptExceptionTest {
    private IOException originalException;
    private ReadFromPromptException wrappedException;

    @Before
    public void setup() {
        originalException = new IOException();
        wrappedException = new ReadFromPromptException("useful message", originalException);
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