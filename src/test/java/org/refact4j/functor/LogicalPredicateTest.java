package org.refact4j.functor;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.refact4j.functor.logical.And;
import org.refact4j.functor.logical.Not;
import org.refact4j.functor.logical.Or;
import org.refact4j.test.XmlTestSuiteHelper;

import java.util.Enumeration;


public class LogicalPredicateTest {

    @Test
    public void testLogicalAnd() {
        BinaryPredicate<Boolean, Boolean> and = new And();
        assertTrue(and.eval(true, true));
        assertFalse(and.eval(true, false));
        assertFalse(and.eval(false, true));
        assertFalse(and.eval(false, false));
    }

    @Test
    public void testLogicalOr() {
        BinaryPredicate<Boolean, Boolean> or = new Or();
        assertTrue(or.eval(true, true));
        assertTrue(or.eval(true, false));
        assertTrue(or.eval(false, true));
        assertFalse(or.eval(false, false));
    }

    @Test
    public void testLogicalNot() {
        UnaryPredicate<Boolean> not = new Not();
        assertTrue(not.apply(false));
        assertFalse(not.apply(true));

    }

    BinaryPredicate<Boolean, Boolean> and = new And();
    BinaryPredicate<Boolean, Boolean> or = new Or();
    UnaryPredicate<Boolean> not = new Not();

    String XML_TEST_SUITE =
            "<testSuite>" +
                    "   <testCase name='LogicalPredicateTest'>" +
                    "       <test name='testLogicalAnd'>" +
                    "           <assertTrue condition='" + and.eval(true, true) + "' />" +
                    "           <assertFalse condition='" + and.eval(true, false) + "' />" +
                    "           <assertFalse condition='" + and.eval(false, true) + "' />" +
                    "           <assertFalse condition='" + and.eval(false, false) + "' />" +
                    "       </test>" +
                    "       <test name='testLogicalOr'>" +
                    "           <assertTrue condition='" + or.eval(true, true) + "' />" +
                    "           <assertTrue condition='" + or.eval(true, false) + "' />" +
                    "           <assertTrue condition='" + or.eval(false, true) + "' />" +
                    "           <assertFalse condition='" + or.eval(false, false) + "' />" +
                    "       </test>" +
                    "       <test name='testLogicalNot'>" +
                    "           <assertTrue condition='" + not.apply(false) + "' />" +
                    "           <assertFalse condition='" + not.apply(true) + "' />" +
                    "       </test>" +
                    "   </testCase>" +
                    "</testSuite>";

    @Test
    public void testXml() throws Throwable {
        TestSuite testSuite = XmlTestSuiteHelper.unmarshal(XML_TEST_SUITE);
        Enumeration<junit.framework.Test> tests = testSuite.tests();
        TestCase test = (TestCase) tests.nextElement();
        Assert.assertEquals("LogicalPredicateTest", test.getName());
        test.runBare();
    }

}
