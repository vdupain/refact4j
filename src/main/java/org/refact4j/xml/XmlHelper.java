package org.refact4j.xml;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.refact4j.util.StringHelper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public final class XmlHelper {
    private static OutputFormat DEFAULT_FORMAT = new OutputFormat();

    static {
        DEFAULT_FORMAT.setLineWidth(80);
        DEFAULT_FORMAT.setIndenting(true);
        DEFAULT_FORMAT.setIndent(2);
        DEFAULT_FORMAT.setLineSeparator(StringHelper.LINE_SEPARATOR);
    }

    private XmlHelper() {

    }

    public static String getAttrValue(String xmlAttrName, XmlAttributes xmlAttributes)
            throws XmlAttributeNotFoundException {
        String value = xmlAttributes.getValue(xmlAttrName);
        if (value == null) {
            throw new XmlAttributeNotFoundException(xmlAttrName);
        }
        return value;
    }

    public static String getAttrValue(String xmlAttrName, XmlAttributes xmlAttributes, String defaultValue) {
        String value = xmlAttributes.getValue(xmlAttrName);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static boolean getBooleanAttrValue(String xmlAttrName, XmlAttributes xmlAttributes, boolean defaultValue) {
        String attrValue = getAttrValue(xmlAttrName, xmlAttributes, Boolean.toString(defaultValue));
        return Boolean.valueOf(attrValue);
    }

    public static int getIntAttrValue(String xmlAttrName, XmlAttributes xmlAttributes, int defaultValue)
            throws NumberFormatException {
        String attrValue = getAttrValue(xmlAttrName, xmlAttributes, Integer.toString(defaultValue));
        return Integer.valueOf(attrValue);
    }

    public static double getDoubleAttrValue(String xmlAttrName, XmlAttributes xmlAttributes, double defaultValue)
            throws NumberFormatException {
        String attrValue = getAttrValue(xmlAttrName, xmlAttributes, Double.toString(defaultValue));
        return Double.valueOf(attrValue);
    }

    public static String prettyPrint(String xml) {
        Writer writer = new StringWriter();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = dbf.newDocumentBuilder().parse(is);
            XMLSerializer serializer = new XMLSerializer(writer, DEFAULT_FORMAT);
            serializer.serialize(document);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
