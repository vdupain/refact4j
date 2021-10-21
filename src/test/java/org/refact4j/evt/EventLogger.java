package org.refact4j.evt;

import junit.framework.Assert;
import junit.framework.ComparisonFailure;
import org.refact4j.util.StringHelper;
import org.refact4j.util.XmlAssert;

import java.util.function.BiPredicate;

public class EventLogger {

    private final StringBuilder buffer = new StringBuilder();

    private Log lastLog;

    public EventLogger() {
        reset();
    }

    public void assertEquals(String expected) {
        checkXml(expected, (expected1, actual) -> {
            try {
                XmlAssert.assertXmlEquals(expected1, actual);
                return true;
            } catch (Throwable ignored) {
            }
            return false;
        });
    }

    private void checkXml(String expected, BiPredicate<String, String> predicate) {
        String actual = closeStream();
        if (expected.length() == 0) {
            fail(expected, actual);
        }
        if (!predicate.test(expected, actual)) {
            fail(expected, actual);
        }
        reset();
    }

    public void assertEquals(EventLogger expected) {
        Assert.assertEquals(expected.closeStream(), closeStream());
    }

    public void assertEmpty() {
        assertEquals("<log/>");
    }

    private void reset() {
        buffer.setLength(0);
        buffer.append("<log>").append(StringHelper.LINE_SEPARATOR);
        lastLog = null;
    }

    public Log log(String eventName) {
        endLastLog();
        lastLog = new Log(eventName);
        return lastLog;
    }

    private void endLastLog() {
        if (lastLog != null) {
            lastLog.end();
        }
    }

    private String closeStream() {
        endLastLog();
        buffer.append("</log>");
        return buffer.toString();
    }

    private void fail(String expected, String actual) {
        expected = expected.replace("<log>", "<log>" + StringHelper.LINE_SEPARATOR);
        expected = expected.replace("/>", "/>" + StringHelper.LINE_SEPARATOR);
        throw new ComparisonFailure("", expected, actual);
    }

    public class Log {
        private Log(String eventName) {
            buffer.append('<').append(eventName);
        }

        public Log add(String key, Object value) {
            add(key, value != null ? value.toString() : "null");
            return this;
        }

        public Log add(String key, String value) {
            buffer.append(' ').append(key).append("=\'").append(value).append("\'");
            return this;
        }

        private void end() {
            buffer.append("/>").append(StringHelper.LINE_SEPARATOR);
        }
    }
}
