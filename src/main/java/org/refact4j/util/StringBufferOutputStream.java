package org.refact4j.util;

import java.io.IOException;
import java.io.OutputStream;

class StringBufferOutputStream extends OutputStream {
    private final StringBuffer stringBuffer;

    public StringBufferOutputStream(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    public void write(int b) throws IOException {
        stringBuffer.append(String.valueOf((char) b));
    }
}