package org.refact4j.xml;

/**
 * ToXmlString interface is an interface that specifies toXmlString method. This interface
 * should be implemented by all objects that can be converted to XML format.
 */
public interface ToXmlString {

    ToXmlString EMPTY_TO_XML = () -> XML.HEADER + "<root/>";


    /**
     * Returns a string that represents this object in XML format.
     *
     * @return The XML as a string.
     */
    String toXmlString();
}
