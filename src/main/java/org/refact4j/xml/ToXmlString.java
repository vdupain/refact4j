package org.refact4j.xml;

/**
 * ToXmlString interface is an interface that specifies toXmlString method. This interface
 * should be implemented by all objects that can be converted to FIELD_XML format.
 */
public interface ToXmlString {

    ToXmlString EMPTY_TO_XML = () -> XML.HEADER + "<root/>";


    /**
     * Returns a string that represents this object in FIELD_XML format.
     *
     * @return The FIELD_XML as a string.
     */
    String toXmlString();
}
