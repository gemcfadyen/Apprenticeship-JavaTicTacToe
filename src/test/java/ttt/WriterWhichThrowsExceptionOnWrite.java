package ttt;

import java.io.IOException;
import java.io.Writer;

public class WriterWhichThrowsExceptionOnWrite extends Writer {

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new IOException("An error has occurred when writing to the console");
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
