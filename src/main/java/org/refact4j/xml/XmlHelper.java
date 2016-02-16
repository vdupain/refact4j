package org.refact4j.xml;

import org.apache.xml.serialize.OutputFormat;
import org.refact4j.util.StringHelper;

public abstract class XmlHelper {
    private static final OutputFormat DEFAULT_FORMAT = new OutputFormat();

    static {
        DEFAULT_FORMAT.setLineWidth(80);
        DEFAULT_FORMAT.setIndenting(true);
        DEFAULT_FORMAT.setIndent(2);
        DEFAULT_FORMAT.setLineSeparator(StringHelper.LINE_SEPARATOR);
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

}
