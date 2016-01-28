package org.refact4j.xml;

import org.refact4j.xml.impl.XMLImpl;

public abstract class AbstractXMLizer<T> implements XMLizer<T> {
    /**
     * Default implementation of a XMLizer calls the toXml method of the
     * ToXml object or </> if the object is null.
     */
    public static final AbstractXMLizer<ToXmlString> DEFAULT = new AbstractXMLizer<ToXmlString>() {

        @Override
        public XML xml(ToXmlString arg) {
            return arg != null ? new XMLImpl(arg) : XML.EMPTY_XML;
        }

    };

    /**
     * Produce a XML representation of an object.
     *
     * @param arg the Object for which a XML should be produced
     * @return xml
     */
    public abstract XML xml(T arg);

    public XML apply(T arg) {
        return this.xml(arg);
    }
}
