package org.refact4j.xml;

import org.refact4j.xml.impl.XMLImpl;

public interface XMLizer<T> extends java.util.function.Function<T, XML> {
    /**
     * Default implementation of a XMLizer calls the toXml method of the
     * ToXml object or </> if the object is null.
     */
    XMLizer<ToXmlString> DEFAULT = new XMLizer<ToXmlString>() {

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
    XML xml(T arg);

    default XML apply(T arg) {
        return this.xml(arg);
    }

}
