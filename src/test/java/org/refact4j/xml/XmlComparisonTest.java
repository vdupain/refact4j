package org.refact4j.xml;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.refact4j.util.XmlAssert;


public class XmlComparisonTest {

    @Test
    public void testNoDiff() throws Exception {
        String xml = "<root/>";
        checkXml(xml, xml, true, true);
    }

    @Test
    public void testDifferent() throws Exception {
        String xml1 = "<root/>";
        String xml2 = "<bye/>";
        checkXml(xml1, xml2, false, false);
    }

    @Test
    public void testChildrenInDifferentOrderAreEquivalentButNotEquals()
            throws Exception {
        String xml1 = "<root><tag1/><tag2/><tag3/></root>";
        String xml2 = "<root><tag3/><tag1/><tag2/></root>";
        checkXml(xml1, xml2, true, false);
    }

    @Test
    public void testAttributeDifference() throws Exception {
        String xml1 = "<root><tag1/><tag2 a=\"A\"/><tag3/></root>";
        String xml2 = "<root><tag3/><tag1/><tag2/></root>";
        checkXml(xml1, xml2, false, false);
    }

    @Test
    public void testAttributeInDifferentOrder() throws Exception {
        String xml1 = "<root><tag1 a=\"A\" b=\"B\"/></root>";
        String xml2 = "<root><tag1 b=\"B\" a=\"A\"/></root>";
        checkXml(xml1, xml2, true, true);
    }


    @Test
    public void testAgainDifferentOrders() throws Exception {
        String xml1 =
                "<root>" +
                        "  <val1>0</val1>" +
                        "  <val2>abcdef</val2>" +
                        "  <val1>1</val1>" +
                        "</root>";
        String xml2 =
                "<root>" +
                        "  <val2>abcdef</val2>" +
                        "  <val1>0</val1>" +
                        "  <val1>1</val1>" +
                        "</root>";
        checkXml(xml1, xml2, true, false);
    }

    @Test
    public void testWithSpacesAndCarriageReturns() throws Exception {
        String xml1 =
                "<root>" +
                        "  <val1    toto='5151'   >  0    \n   </val1>" +
                        "  \n    <val2>abcdef           </val2>" +
                        "</root>";
        String xml2 =
                "<root><val1 toto='5151'>0</val1><val2>abcdef</val2></root>";

        checkXml(xml1, xml2, true, true);
    }

    @Test
    public void testIdenticalTagCountAreTakenIntoAccount() throws Exception {
        String xml1 =
                "<parent>" +
                        "  <child/>" +
                        "  <child/>" +
                        "</parent>";
        String xml2 =
                "<parent>" +
                        "  <child/>" +
                        "  <child/>" +
                        "  <child/>" +
                        "</parent>";
        checkXml(xml1, xml2, false, false);
    }

    @Test
    public void testSameCharacters() throws Exception {
        String xml1 = "<root><tag1>XYZ</tag1></root>";
        String xml2 = "<root><tag1>XYZ</tag1></root>";
        checkXml(xml1, xml2, true, true);
    }

    @Test
    public void testWhitespace() throws Exception {
        String xml1 =
                "<root>" +
                        "  <list>" +
                        "    <tag1>XYZ</tag1>" +
                        "    <tag2>PQR</tag2>" +
                        "    <tag3>" +
                        "      <tag4/>" +
                        "    </tag3>" +
                        "  </list>" +
                        "</root>";
        String xml2 =
                "<root><list><tag1>XYZ</tag1><tag2>PQR</tag2><tag3><tag4/></tag3></list></root>";
        checkXml(xml1, xml2, true, true);
        checkXml(xml2, xml1, true, true);
    }

    @Test
    public void testNotSameCharacters() throws Exception {
        String xml1 = "<root><tag1>ABC</tag1></root>";
        String xml2 = "<root><tag1>XYZ</tag1></root>";
        checkXml(xml1, xml2, false, false);
    }

    @Test
    public void testSame() throws Exception {
        String xml1 = "<root></root>";
        String xml2 = "<root/>";
        checkXml(xml1, xml2, true, true);
    }

    private void checkXml(String xml1, String xml2, boolean isEquivalent,
                          boolean isEqual) {
        if (isEqual) {
            assertTrue(isEquivalent);
            XmlAssert.assertXmlEquivalent(xml1, xml2);
            XmlAssert.assertXmlEquals(xml1, xml2);
        } else {
            if (!isEquivalent) {
                checkXmlAssertEquivalentFails(xml1, xml2);
            } else {
                XmlAssert.assertXmlEquivalent(xml1, xml2);
            }
            checkXmlAssertEqualsFails(xml1, xml2);
        }
    }

    private void checkXmlAssertEquivalentFails(String xml1,
                                               String xml2) {
        try {
            XmlAssert.assertXmlEquivalent(xml1, xml2);
            fail();
        } catch (Throwable e) {
        }
    }

    private void checkXmlAssertEqualsFails(String xml1,
                                           String xml2) {
        try {
            XmlAssert.assertXmlEquals(xml1, xml2);
            fail();
        }
        catch (Throwable e) {
        }
    }

}
