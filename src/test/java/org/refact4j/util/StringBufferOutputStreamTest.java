package org.refact4j.util;

import org.junit.Assert;
import org.junit.Test;

public class StringBufferOutputStreamTest {

    @Test
    public void test1() throws Exception {
        String s = "abc";
        StringBuffer stringBuffer = new StringBuffer();
        StringBufferOutputStream stream =
                new StringBufferOutputStream(stringBuffer);
        stream.write(s.charAt(0));
        stream.write(s.charAt(1));
        stream.write(s.charAt(2));
        Assert.assertEquals(s, stringBuffer.toString());

    }
}
