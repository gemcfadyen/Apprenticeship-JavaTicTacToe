package ttt;

import java.io.IOException;
import java.io.Reader;

public class ReaderStubWhichThrowsExceptionOnRead extends Reader {

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw new IOException("Always throw an exception for testing");
    }

    @Override
    public void close() throws IOException {
    }
}
