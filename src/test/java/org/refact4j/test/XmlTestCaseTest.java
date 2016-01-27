package org.refact4j.test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;

public class XmlTestCaseTest {

    private static String XML_TEST_SUITE =
            "<testSuite>" +
                    "   <testCase name='testCase1'>" +
                    "      <before handler='" + PrinterBeforeTestHandler.class.getName() + "'/>" +
                    "     <after handler='" + PrinterAfterTestHandler.class.getName() + "'/>" +
                    "       <test name='test1'>" +
                    "           <assertEquals>" +
                    "               <expected>a</expected>" +
                    "               <actual>a</actual>" +
                    "           </assertEquals>" +
                    "           <assertEquals message='compare a and b' >" +
                    "               <expected>a</expected>" +
                    "               <actual>b</actual>" +
                    "           </assertEquals>" +
                    "       </test>" +
                    "       <test name='test2'/>" +
                    "   </testCase>" +
                    "   <testCase name='dummyTestCase'>" +
                    "       <before handler='" + DummyBeforeTestHandler.class.getName() + "'/>" +
                    "       <after handler='" + DummyAfterTestHandler.class.getName() + "'/>" +
                    "       <test name='dummyTest'>" +
                    "           <assertEquals message='compare xml' >" +
                    "               <expected><![CDATA[<root><tag1/><tag2/><tag3/></root>]]></expected>" +
                    "               <actual><![CDATA[<root><tag3/><tag1/><tag2/></root>]]></actual>" +
                    "           </assertEquals>" +
                    "       </test>" +
                    "   </testCase>" +
                    "   <testCase name='testCase3'>" +
                    "       <test name='assertConditionTesting'>" +
                    "           <assertTrue message='assert true' condition='true' />" +
                    "           <assertFalse message='assert false' condition='false' />" +
                    "       </test>" +
                    "   </testCase>" +
                    "</testSuite>";

    @Test
    public void test1() throws Throwable {
        TestSuite testSuite = XmlTestSuiteHelper.unmarshal(XML_TEST_SUITE);
        Assert.assertEquals("testSuite", testSuite.getName());
        Enumeration<junit.framework.Test> tests = testSuite.tests();
        TestCase test = (TestCase) tests.nextElement();
        Assert.assertEquals("testCase1", test.getName());
        try {
            test.runBare();
            Assert.fail("Exception expected");
        } catch (Throwable t) {
            Assert.assertEquals("compare a and b expected:<[a]> but was:<[b]>", t.getMessage());
        }

        test = (TestCase) tests.nextElement();
        Assert.assertEquals("dummyTestCase", test.getName());
        try {
            test.runBare();
            Assert.fail("Exception expected");
        } catch (Throwable t) {
            Assert.assertEquals("compare xml expected:<<root><tag[1/><tag2/><tag3]/></root>> but was:<<root><tag[3/><tag1/><tag2]/></root>>", t.getMessage());
        }

        test = (TestCase) tests.nextElement();
        Assert.assertEquals("testCase3", test.getName());
        test.runBare();
    }

    public static class PrinterBeforeTestHandler implements BeforeTestHandler {
        public Object apply(XmlTestCase arg) {
            System.out.println("PrinterBeforeTestFunctor.apply()=" + arg.getName());
            return null;
        }
    }

    public static class DummyBeforeTestHandler implements BeforeTestHandler {
        public Object apply(XmlTestCase arg) {
            return null;
        }
    }

    public static class PrinterAfterTestHandler implements AfterTestHandler {
        public Object apply(XmlTestCase arg) {
            System.out.println("PrinterAfterTestHandler.apply()=" + arg.getName());
            return null;
        }
    }

    public static class DummyAfterTestHandler implements AfterTestHandler {
        public Object apply(XmlTestCase arg) {
            return null;
        }
    }

}
