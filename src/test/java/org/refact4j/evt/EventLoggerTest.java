package org.refact4j.evt;

import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.refact4j.util.AssertionFailureNotDetectedError;


public class EventLoggerTest {
    EventLogger eventLogger = new EventLogger();

    @Test
    public void testEventWithoutArgs() throws Exception {
        eventLogger.log("evt");
        eventLogger.assertEquals("<log>" + "<evt/>" + "</log>");
    }

    @Test
    public void testStandardCase() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add("key2", "value2");
        eventLogger.log("evt2").add("key3", "value3").add("key4", "value4");
        eventLogger.assertEquals("<log>"
                + "<evt1 key1='value1' key2='value2'/>"
                + "<evt2 key3='value3' key4='value4'/>" + "</log>");
    }

    @Test
    public void testErrorCaseWithWrongValue() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add("key2", "value2");
        eventLogger.log("evt2").add("key3", "value3").add("key4", "value4");
        checkError("<log>"
                + "<evt1 key1='value1' key2='AnotherValueWhichIsNotReallyTheExpectedOne'/>"
                + "<evt2 key3='value3' key4='value4'/>" + "</log>");
    }

    @Test
    public void testErrorCaseWithAdditionalKey() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add("key2", "value2");
        eventLogger.log("evt2").add("key3", "value3").add("key4", "value4");
        checkError("<log>"
                + "<evt1 key1='value1' key2='value2' ThisIsAnUnexpectedKey='unexpectedValue'/>"
                + "<evt2 key3='value3' key4='value4'/>" + "</log>");
    }

    @Test
    public void testErrorCaseWithMissingKey() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add(
                "ThisOneWillbeMissing", "value");
        eventLogger.log("evt2").add("key3", "value3").add("key4", "value4");
        checkError("<log>" + "<evt1 key1='value1'/>"
                + "<evt2 key3='value3' key4='value4'/>" + "</log>");
    }

    @Test
    public void testErrorCaseWithAdditionalEvent() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add("key2", "value2");
        eventLogger.log("evt2").add("key3", "value3").add("key4", "value4");
        checkError("<log>" + "<evt1 key1='value1' key2='value2'/>"
                + "<evt2 key3='value3' key4='value4'/>"
                + "<AdditionalEvent key='value'/>" + "</log>");
    }

    @Test
    public void testErrorCaseWithMissingEvent() throws Exception {
        eventLogger.log("evt1").add("key1", "value1").add("key2", "value2");
        eventLogger.log("ThisOneWillBeMissing").add("key3", "value3").add(
                "key4", "value4");
        checkError("<log>" + "<evt1 key1='value1' key2='value2'/>" + "</log>");
    }

    @Test
    public void testOrderIsSignificant() throws Exception {
        eventLogger.log("evt1");
        eventLogger.log("evt2");
        checkError("<log>" + "  <evt2/>" + "  <evt1/>" + "</log>");
    }

    @Test
    public void testListenerIsResetAfterEveryCheck() throws Exception {
        eventLogger.log("evt1");
        eventLogger.assertEquals("<log>" + "<evt1/>" + "</log>");
        eventLogger.log("evt2");
        eventLogger.assertEquals("<log>" + "<evt2/>" + "</log>");
    }

    @Test
    public void testEmptyExpectedString() throws Exception {
        try {
            eventLogger.assertEquals("");
            throw new AssertionFailureNotDetectedError();
        } catch (AssertionFailedError e) {
        }
    }

    @Test
    public void testAssertEmpty() throws Exception {
        eventLogger.assertEmpty();
    }

    @Test
    public void testAssertEmptyError() throws Exception {
        eventLogger.log("UnexpectedEvent");
        try {
            eventLogger.assertEmpty();
            throw new AssertionFailureNotDetectedError();
        } catch (AssertionFailedError e) {
        }
    }

    @Test
    public void testAssertWithXmlTestLogger() throws Exception {
        eventLogger.log("evt1").add("key", "value");
        EventLogger expected = new EventLogger();
        expected.log("evt1").add("key", "value");
        eventLogger.assertEquals(expected);
    }

    @Test
    public void testAssertXmlTestLoggerFailsWithXmlTestLoggerEmpty()
            throws Exception {
        eventLogger.log("evt1");

        EventLogger expected = new EventLogger();
        try {
            eventLogger.assertEquals(expected);
            throw new AssertionFailureNotDetectedError();
        } catch (AssertionFailedError e) {
        }
    }

    @Test
    public void testAssertXmlTestLoggerFailsXmlTestLoggerNotEmpty()
            throws Exception {
        eventLogger.log("evt1");

        EventLogger expected = new EventLogger();
        expected.log("evt1");
        expected.log("evt2");
        try {
            eventLogger.assertEquals(expected);
            throw new AssertionFailureNotDetectedError();
        } catch (AssertionFailedError e) {
        }
    }

    private void checkError(String s) throws Exception {
        try {
            eventLogger.assertEquals(s);
            throw new AssertionFailureNotDetectedError();
        } catch (AssertionFailedError e) {
        }
    }
}
