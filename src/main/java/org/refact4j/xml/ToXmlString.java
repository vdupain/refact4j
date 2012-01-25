package org.refact4j.xml;

/**
 * ToXmlString interface is an interface that specifies toXmlString method. This interface
 * should be implemented by all objects that can be converted to XML format.
 */
public interface ToXmlString {

    public static final ToXmlString EMPTY_TO_XML = new ToXmlString() {
        public String toXmlString() {
            return XML.HEADER + "<root/>";
        }
    };


    /**
     * Returns a string that represents this object in XML format.
     *
     * @return The XML as a string.
     */
    String toXmlString();
}
