package org.refact4j.function;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;
import org.refact4j.function.logical.And;
import org.refact4j.function.logical.Not;
import org.refact4j.function.logical.Or;
import org.refact4j.test.XmlTestSuiteHelper;

import java.util.Enumeration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LogicalPredicateTest {

    BinaryPredicate<Boolean, Boolean> and = new And();
    BinaryPredicate<Boolean, Boolean> or = new Or();
    UnaryPredicate<Boolean> not = new Not();
    String XML_TEST_SUITE =
            "<testSuite>" +
                    "   <testCase name='LogicalPredicateTest'>" +
                    "       <test name='testLogicalAnd'>" +
                    "           <assertTrue condition='" + and.apply(true, true) + "' />" +
                    "           <assertFalse condition='" + and.apply(true, false) + "' />" +
                    "           <assertFalse condition='" + and.apply(false, true) + "' />" +
                    "           <assertFalse condition='" + and.apply(false, false) + "' />" +
                    "       </test>" +
                    "       <test name='testLogicalOr'>" +
                    "           <assertTrue condition='" + or.apply(true, true) + "' />" +
                    "           <assertTrue condition='" + or.apply(true, false) + "' />" +
                    "           <assertTrue condition='" + or.apply(false, true) + "' />" +
                    "           <assertFalse condition='" + or.apply(false, false) + "' />" +
                    "       </test>" +
                    "       <test name='testLogicalNot'>" +
                    "           <assertTrue condition='" + not.apply(false) + "' />" +
                    "           <assertFalse condition='" + not.apply(true) + "' />" +
                    "       </test>" +
                    "   </testCase>" +
                    "</testSuite>";

    @Test
    public void testLogicalAnd() {
        BinaryPredicate<Boolean, Boolean> and = new And();
        assertTrue(and.apply(true, true));
        assertFalse(and.apply(true, false));
        assertFalse(and.apply(false, true));
        assertFalse(and.apply(false, false));
    }

    @Test
    public void testLogicalOr() {
        BinaryPredicate<Boolean, Boolean> or = new Or();
        assertTrue(or.apply(true, true));
        assertTrue(or.apply(true, false));
        assertTrue(or.apply(false, true));
        assertFalse(or.apply(false, false));
    }

    @Test
    public void testLogicalNot() {
        UnaryPredicate<Boolean> not = new Not();
        assertTrue(not.apply(false));
        assertFalse(not.apply(true));

    }

    @Test
    public void testXml() throws Throwable {
        TestSuite testSuite = XmlTestSuiteHelper.unmarshal(XML_TEST_SUITE);
        Enumeration<junit.framework.Test> tests = testSuite.tests();
        TestCase test = (TestCase) tests.nextElement();
        Assert.assertEquals("LogicalPredicateTest", test.getName());
        test.runBare();
    }

}
